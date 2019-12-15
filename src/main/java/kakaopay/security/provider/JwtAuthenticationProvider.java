package kakaopay.security.provider;

import com.auth0.jwt.interfaces.DecodedJWT;
import kakaopay.security.token.JwtPostAuthorizeToken;
import kakaopay.security.token.JwtPreAuthorizeToken;
import kakaopay.utils.JwtFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final JwtFactory jwtFactory;

    public JwtAuthenticationProvider(JwtFactory jwtFactory) {
        this.jwtFactory = jwtFactory;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtPreAuthorizeToken jwtPreAuthorizeToken = (JwtPreAuthorizeToken) authentication;
        String token = (String) jwtPreAuthorizeToken.getPrincipal();
        DecodedJWT decode = jwtFactory.verify(token);

        String userId = decode.getClaim("userId").asString();

        return new JwtPostAuthorizeToken(userId, "ROLE_USER");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(JwtPreAuthorizeToken.class);
    }
}
