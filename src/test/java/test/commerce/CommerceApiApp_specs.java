package test.commerce;

import commerce.CommerceApiApp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = CommerceApiApp.class)
public class CommerceApiApp_specs {

    @Test
    void 컨텍스트가_초기화된다() {
    }

    @Test
    void PasswordEncoder_빈이_올바르게_설정된다(
        @Autowired PasswordEncoder actual
    ) {
        assertThat(actual).isInstanceOf(Pbkdf2PasswordEncoder.class);
    }
}
