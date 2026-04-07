package com.poly.sneakerstore.config;

import com.poly.sneakerstore.model.Role;
import com.poly.sneakerstore.model.User;
import com.poly.sneakerstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ApplicationInitConfig {

    private final PasswordEncoder passwordEncoder;

    @Bean
    public ApplicationRunner applicationRunner(UserRepository userRepository) {
        return application -> {
            if (!userRepository.existsByEmail("phuonglam.dev@gmail.com")) {
                User user = User.builder()
                        .email("phuonglam.dev@gmail.com")
                        .password(passwordEncoder.encode("admin@123"))
                        .role(Role.ADMIN)
                        .build();
                userRepository.save(user);
                log.warn("Admin has been created with default password: admin@123");
            }
        };
    }
}
