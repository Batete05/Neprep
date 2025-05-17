package com.eucl.services;


import com.eucl.dto.request.MeterCreateDTO;
import com.eucl.exceptions.CustomRequestException;
import com.eucl.model.Meter;
import com.eucl.model.Role;
import com.eucl.model.User;
import com.eucl.repository.MeterRepository;
import com.eucl.utils.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class MeterService {

    private final MeterRepository meterRepository;


    public Meter createMeter(MeterCreateDTO meterCreateDTO) throws CustomRequestException {
        User currentUser = CurrentUser.getCurrentUser();

        if (currentUser.getRole() != Role.ROLE_ADMIN) {
            throw new CustomRequestException(HttpStatus.UNAUTHORIZED,"You do not have permission to perform this action");
        }

        Meter meter = new Meter();
        meter.setMeterNumber(meterCreateDTO.getMeterNumber());
        meter.setNationalId(meterCreateDTO.getNationalId());
        meterRepository.save(meter);
        return meter;
    }



    public static String generateSixDigitNumber() {
        Random random = new Random();
        int number = random.nextInt(1_000_000); // Generates 0 to 999999
        return String.format("%06d", number);   // Pads with zeros if needed
    }


}
