package com.eucl.config;

import com.eucl.model.Meter;
import com.eucl.model.Role;
import com.eucl.model.User;
import com.eucl.repository.MeterRepository;
import com.eucl.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SeedAdmin {

    @Bean
    public CommandLineRunner insertInitialMeter(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!userRepository.existsByRole(Role.ROLE_ADMIN)) {
                User user = new User();
                user.setPassword(passwordEncoder.encode("admin123123"));
                user.setRole(Role.ROLE_ADMIN);
                user.setNames("admin");
                user.setNationalId("11999781234560237");
                user.setEmail("admin@admin.com");
                user.setPhone("0786743673");
                userRepository.save(user);
                System.out.println("admin initialed");
            }
        };
    }

    private String generateSixDigitNumber() {
        return String.format("%06d", (int)(Math.random() * 1_000_000));
    }
}
