package kakaopay.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import kakaopay.domain.Account;
import kakaopay.exception.JwtCreateException;
import org.springframework.stereotype.Component;

@Component
public class JwtFactory {
    private final JwtConfig jwtConfig;

    public JwtFactory(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String createToken(Account account) {
        String token = null;

        try {
            token = JWT.create()
                    .withIssuer(jwtConfig.getIssuer())
                    .withClaim("userId", account.getUserId())
                    .sign(generateAlgorithm());
        } catch (IllegalArgumentException e) {
            throw new JwtCreateException();
        }
        return token;
    }

    private Algorithm generateAlgorithm() {
        return Algorithm.HMAC256(jwtConfig.getSignKey());
    }
}
