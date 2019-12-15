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

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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
                    .withExpiresAt(Date.from(LocalDateTime.now().plusHours(1L).atZone(ZoneId.systemDefault()).toInstant()))
                    .sign(generateAlgorithm());
        } catch (IllegalArgumentException e) {
            throw new JwtCreateException();
        }
        return token;
    }

    public DecodedJWT verify(String token) {
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

    public DecodedJWT refreshVerify(String token) {
        String newToken;
        DecodedJWT jwt = JWT.decode(token);
        try {
            newToken = JWT.create()
                    .withIssuer(jwtConfig.getIssuer())
                    .withClaim("userId", jwt.getClaim("userId").asString())
                    .withExpiresAt(jwt.getExpiresAt())
                    .sign(generateAlgorithm());
            if (token.equals(newToken)) {
                return jwt;
            }
        } catch (Exception e) {
            throw new InvalidJwtException();
        }
        throw new InvalidJwtException();
    }

    private Algorithm generateAlgorithm() {
        return Algorithm.HMAC256(jwtConfig.getSignKey());
    }
}
