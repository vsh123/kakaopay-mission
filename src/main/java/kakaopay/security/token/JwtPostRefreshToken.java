package kakaopay.security.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtPostRefreshToken extends UsernamePasswordAuthenticationToken {
    public JwtPostRefreshToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public JwtPostRefreshToken(String token) {
        this(token, token, null);
    }
}
