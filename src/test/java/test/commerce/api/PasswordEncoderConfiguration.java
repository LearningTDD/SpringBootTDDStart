package test.commerce.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import static org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256;

public class PasswordEncoderConfiguration {

    public static final int SALT_LENGTH = 16;
    public static final int ITERATIONS = 10;

    @Bean
    @Primary
    Pbkdf2PasswordEncoder testPasswordEncoder() {
        return new Pbkdf2PasswordEncoder(
            "",
            SALT_LENGTH,
            ITERATIONS,
            PBKDF2WithHmacSHA256
        );
    }
}
