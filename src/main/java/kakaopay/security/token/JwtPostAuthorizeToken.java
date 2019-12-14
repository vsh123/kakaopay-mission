package kakaopay.security.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;

public class JwtPostAuthorizeToken extends UsernamePasswordAuthenticationToken {
    public JwtPostAuthorizeToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public JwtPostAuthorizeToken(String userId, String authority) {
        super(userId, "", Arrays.asList(new SimpleGrantedAuthority(authority)));
    }
}
