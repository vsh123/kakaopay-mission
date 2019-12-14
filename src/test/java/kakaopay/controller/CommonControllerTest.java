package kakaopay.controller;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.config.location=classpath:/jwt.yml")
public class CommonControllerTest {
    public static final String AUTHRIZATION_HEADER = "Authorization";
    public static final String PREFIX = "Bearer ";

    @Autowired
    protected WebTestClient webTestClient;

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
}
