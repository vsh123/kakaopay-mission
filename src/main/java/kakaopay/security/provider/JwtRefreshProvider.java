package kakaopay.security.provider;

import com.auth0.jwt.interfaces.DecodedJWT;
import kakaopay.domain.Account;
import kakaopay.domain.AccountRepository;
import kakaopay.exception.InvalidJwtException;
import kakaopay.security.token.JwtPostRefreshToken;
import kakaopay.security.token.JwtPreRefreshToken;
import kakaopay.utils.JwtFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtRefreshProvider implements AuthenticationProvider {
    private final JwtFactory jwtFactory;
    private final AccountRepository accountRepository;

    public JwtRefreshProvider(JwtFactory jwtFactory, AccountRepository accountRepository) {
        this.jwtFactory = jwtFactory;
        this.accountRepository = accountRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtPreRefreshToken refreshToken = (JwtPreRefreshToken) authentication;
        String token = (String) refreshToken.getPrincipal();

        DecodedJWT decodedJWT = jwtFactory.decode(token);
        String userId = decodedJWT.getClaim("userId").asString();

        Account account = accountRepository.findByUserId(userId)
                .orElseThrow(InvalidJwtException::new);
        String newToken = jwtFactory.createToken(account);
        return new JwtPostRefreshToken(newToken);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(JwtPreRefreshToken.class);
    }
}
