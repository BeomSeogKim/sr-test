package sr.backend.test.common.password;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncoder {

    public static String encryptPassword(String originalPassword) {
        return BCrypt.hashpw(originalPassword, BCrypt.gensalt());
    }

    public static boolean checkPassword(String originalPassword, String hashedPassword) {
        return BCrypt.checkpw(originalPassword, hashedPassword);
    }
}
