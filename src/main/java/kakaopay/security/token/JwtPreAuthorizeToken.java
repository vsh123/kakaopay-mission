package kakaopay.security.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JwtPreAuthorizeToken extends UsernamePasswordAuthenticationToken {
    public JwtPreAuthorizeToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public JwtPreAuthorizeToken(String token) {
        super(token, token);
    }
}
