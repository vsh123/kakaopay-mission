package kakaopay.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import kakaopay.domain.Account;
import kakaopay.exception.InvalidJwtException;
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

    public DecodedJWT decode(String token) {
        DecodedJWT jwt = null;

        try {
            Algorithm algorithm = generateAlgorithm();
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(jwtConfig.getIssuer())
                    .build();
            jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            throw new InvalidJwtException();
        }
        return jwt;
    }

    private Algorithm generateAlgorithm() {
        return Algorithm.HMAC256(jwtConfig.getSignKey());
    }
}
