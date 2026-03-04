package com.zzemilio.forohub.config;


import com.zzemilio.forohub.domain.User;
import com.zzemilio.forohub.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        String email = "admin@forohub.com";
        String pass = "123456";

        userRepository.findByEmail(email).orElseGet(() ->
                userRepository.save(new User(email, passwordEncoder.encode(pass)))
        );
    }
}