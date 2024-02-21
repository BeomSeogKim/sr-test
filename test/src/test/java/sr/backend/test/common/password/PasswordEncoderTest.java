package sr.backend.test.common.password;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PasswordEncoderTest {

    @DisplayName("Password Encoder 를 사용해서 암호화를 진행할 수 있다.")
    @Test
    void encryptPassword() {
        // given
        String originalPassword = "password";

        // when
        String hashedPassword = PasswordEncoder.encryptPassword(originalPassword);

        // then
        assertThat(hashedPassword).isNotEqualTo(originalPassword);
    }

    @DisplayName("checkPassword를 사용해 암호화 되기전 비밀번호와 동일성을 검증 할 수 있다.")
    @Test
    void checkPassword() {
        // given
        String originalPassword = "password";
        String hashedPassword = PasswordEncoder.encryptPassword(originalPassword);
        // when
        boolean result = PasswordEncoder.checkPassword(originalPassword, hashedPassword);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("암호화 된 후의 비밀번호와 기존 비밀번호가 다를경우 checkPassword의 결과는 false이어야 한다.")
    @Test
    void checkPassword_false() {
        // given
        String originalPassword = "password";
        String hashedPassword = PasswordEncoder.encryptPassword(originalPassword);
        // when
        boolean result = PasswordEncoder.checkPassword("new_password", hashedPassword);

        // then
        assertThat(result).isFalse();
    }

}