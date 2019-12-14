package kakaopay.controller;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.BodyInserters;

import static org.assertj.core.api.Assertions.assertThat;

class AccountControllerTest extends CommonControllerTest {
    @Test
    void 회원가입_테스트() {
        String token = webTestClient.post()
                .uri("/api/signup")
                .body(BodyInserters.fromFormData("userId", "userId")
                        .with("password", "password"))
                .exchange()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        assertThat(token.split("\\.").length).isEqualTo(3);
    }

    @Test
    void 로그인_테스트() {
        String userId = "van";
        String password = "van";
        signUp(userId, password);
        String token = webTestClient.post()
                .uri("/api/login")
                .body(BodyInserters.fromFormData("userId", userId)
                        .with("password", password))
                .exchange()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        assertThat(token.split("\\.").length).isEqualTo(3);

    }

    public void signUp(String userId, String password) {
        webTestClient.post()
                .uri("/api/signup")
                .body(BodyInserters.fromFormData("userId", userId)
                        .with("password", password))
                .exchange();

    }
}