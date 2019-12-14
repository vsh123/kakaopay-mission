package kakaopay.security.filter;

import kakaopay.security.token.JwtPreRefreshToken;
import kakaopay.utils.JwtExtractor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static kakaopay.security.filter.JwtAuthenticationFilter.AUTHORIZATION;

public class JwtRefreshFilter extends AbstractAuthenticationProcessingFilter {
    public JwtRefreshFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String header = request.getHeader(AUTHORIZATION);
        String token = JwtExtractor.extract(header);
        JwtPreRefreshToken jwtPreAuthorizeToken = new JwtPreRefreshToken(token, token);
        return super.getAuthenticationManager().authenticate(jwtPreAuthorizeToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = (String) authResult.getPrincipal();
        response.setStatus(HttpStatus.OK.value());
        response.getWriter().write(token);
    }
}
