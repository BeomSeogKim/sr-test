package sr.backend.test.common.password;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenProvider {

    @Value("${test.token.key}")
    private String SECRET_KEY;

    public String createToken(String memberId) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 1800000);

        return Jwts.builder()
            .setSubject(memberId)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact();
    }
}
