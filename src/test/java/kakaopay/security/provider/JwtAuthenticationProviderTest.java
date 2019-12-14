package kakaopay.security.provider;

import kakaopay.security.token.JwtPreAuthorizeToken;
import kakaopay.utils.JwtFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class JwtAuthenticationProviderTest {
    @Mock
    private JwtFactory jwtFactory;

    @InjectMocks
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Test
    void support() {
        assertThat(jwtAuthenticationProvider.supports(JwtPreAuthorizeToken.class)).isTrue();
    }
}