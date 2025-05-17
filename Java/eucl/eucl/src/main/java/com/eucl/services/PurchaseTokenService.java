package com.eucl.services;

import com.eucl.dto.request.PurchaseTokenCreateDTO;
import com.eucl.dto.response.PurchaseTokenResponseDTO;
import com.eucl.mapper.PurchaseTokenMapper;
import com.eucl.model.*;
import com.eucl.repository.MeterRepository;
import com.eucl.repository.NotificationRepository;
import com.eucl.repository.PurchasedTokenRepository;
import com.eucl.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class PurchaseTokenService {

    private final PurchasedTokenRepository purchasedTokenRepository;
    private final MeterRepository meterRepository;
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PurchaseTokenMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;

    public PurchaseTokenResponseDTO create(PurchaseTokenCreateDTO purchaseTokenCreateDTO) throws BadRequestException {
        Meter meter = meterRepository.findByMeterNumber(purchaseTokenCreateDTO.getMeterNumber())
                .orElseThrow(() -> new BadRequestException("Meter number not exist"));

        if (!isMultipleOf100(purchaseTokenCreateDTO.getAmount())) {
            throw new BadRequestException("Amount must be a multiple of 100");
        }

        PurchasedToken purchasedToken = new PurchasedToken();
        purchasedToken.setToken(generateSixteenDigitNumber());
        purchasedToken.setTokenStatus(TokenStatus.NEW);
        purchasedToken.setMeter(meter);
        purchasedToken.setAmount(purchaseTokenCreateDTO.getAmount());
        purchasedToken.setTokenValueDays(purchasedToken.getAmount() / 100);
        purchasedTokenRepository.save(purchasedToken);

        return mapper.toPurchasedTokenDTO(purchasedToken);
    }

    public List<PurchasedToken> searchTokenByMeterNumber(String meterNumber) {
        return purchasedTokenRepository.findAllByMeter_MeterNumber(meterNumber);
    }

    public void checkExpiringTokens() {
        List<PurchasedToken> expiringTokens = entityManager
                .createNativeQuery("SELECT * FROM find_expiring_tokens()", PurchasedToken.class)
                .getResultList();

        expiringTokens.forEach(token -> {
            User user = userRepository.findByNationalId(token.getMeter().getNationalId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Notification notification = new Notification();
            notification.setMeter(token.getMeter());
            notification.setMessage(String.format(
                    "Dear %s, REG is pleased to remind you that the token in the meter %s is going to expire in 5 hours. Please purchase a new token.",
                    user.getNames(),
                    token.getMeter().getMeterNumber()
            ));

            notificationRepository.save(notification);
            emailService.sendEmail(user.getEmail(), notification.getMessage());
        });
    }

    @Scheduled(fixedRate = 30000) // Runs every 30 seconds (30,000 milliseconds)
    public void runCheck() {
        checkExpiringTokens();
    }


    public boolean isMultipleOf100(int number) {
        return number % 100 == 0;
    }

    public static String generateSixteenDigitNumber() {
        Random random = new Random();
        int firstPart = 10000000 + random.nextInt(90000000);  // 8-digit number
        int secondPart = 10000000 + random.nextInt(90000000); // 8-digit number
        return String.format("%08d%08d", firstPart, secondPart);
    }

}
