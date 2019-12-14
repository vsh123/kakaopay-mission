package kakaopay.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import kakaopay.utils.JwtFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.config.location=classpath:/jwt.yml")
public class CommonControllerTest {
    public static final String AUTHRIZATION_HEADER = "Authorization";
    public static final String PREFIX = "Bearer ";

    @Autowired
    protected WebTestClient webTestClient;
    @Autowired
    protected JwtFactory jwtFactory;

    protected String token;

    @BeforeEach
    void setUp() {
        token = getToken();
    }

    public String getToken() {
        String userId = "van";
        String password = "van";
        return webTestClient.post()
                .uri("/api/login")
                .body(BodyInserters.fromFormData("userId", userId)
                        .with("password", password))
                .exchange()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();
    }

    @Test
    void token_Refresh_테스트() {
        String newToken = webTestClient.get()
                .uri("/api/refresh")
                .header(AUTHRIZATION_HEADER, PREFIX + token)
                .exchange()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        DecodedJWT preJWT = jwtFactory.decode(token);
        DecodedJWT newJWT = jwtFactory.decode(newToken);
        String preUserId = preJWT.getClaim("userId").asString();
        String newTokenUserId = newJWT.getClaim("userId").asString();
        assertThat(preUserId).isEqualTo(newTokenUserId);
    }
}
