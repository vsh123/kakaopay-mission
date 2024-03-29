package kakaopay.security;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthenticationMatcher implements RequestMatcher {
    private OrRequestMatcher orRequestMatcher;

    public JwtAuthenticationMatcher(List<String> skipPaths) {
        orRequestMatcher = new OrRequestMatcher(skipPaths.stream().map(AntPathRequestMatcher::new).collect(Collectors.toList()));
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        return !orRequestMatcher.matches(request);
    }
}
