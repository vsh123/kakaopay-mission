package kakaopay.utils;

import kakaopay.domain.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtFactoryTest {
    @Mock
    private JwtConfig jwtConfig;

    @InjectMocks
    private JwtFactory jwtFactory;

    @Test
    void createTokenTest() {
        Account account = new Account("userId", "password");

        when(jwtConfig.getIssuer()).thenReturn("van");
        when(jwtConfig.getSignKey()).thenReturn("test");

        String token = jwtFactory.createToken(account);
        assertThat(token.split("\\.").length).isEqualTo(3);
    }
}