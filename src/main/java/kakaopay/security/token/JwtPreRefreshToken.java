package kakaopay.security.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JwtPreRefreshToken extends UsernamePasswordAuthenticationToken {
    public JwtPreRefreshToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public JwtPreRefreshToken(String token) {
        this(token, token);
    }
}
