package kakaopay.utils;

import com.auth0.jwt.interfaces.DecodedJWT;
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

    @Test
    void decodeTest() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzaGFrZXZhbiIsInVzZXJJZCI6InVzZXJJZCJ9.-ydGZvmEr8t8mePyT1kKivilf1Mrpj2juyOkoc8d5Vs";

        when(jwtConfig.getIssuer()).thenReturn("shakevan");
        when(jwtConfig.getSignKey()).thenReturn("shakevan");

        DecodedJWT decodedJWT = jwtFactory.decode(token);

        assertThat(decodedJWT.getClaim("userId").asString()).isEqualTo("userId");
    }
}