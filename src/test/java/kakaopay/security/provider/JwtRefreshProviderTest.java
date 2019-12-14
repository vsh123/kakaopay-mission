package kakaopay.security.provider;

import kakaopay.security.token.JwtPreRefreshToken;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class JwtRefreshProviderTest {
    @InjectMocks
    private JwtRefreshProvider jwtRefreshProvider;

    @Test
    void supports() {
        assertThat(jwtRefreshProvider.supports(JwtPreRefreshToken.class)).isTrue();
    }
}