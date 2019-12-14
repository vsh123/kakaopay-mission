package kakaopay.controller;

import kakaopay.dto.RegionSupportInfoResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.BodyInserters;

import static org.assertj.core.api.Assertions.assertThat;

class RegionSupInfoControllerTest extends CommonControllerTest {
    @Test
    void updateTest() {
        String target = "updateTarget";
        String usage = "updateUsage";
        String limitPay = "updateLimitPay";

        RegionSupportInfoResponseDto responseDto = webTestClient.put()
                .uri("/api/regionsupinfos")
                .body(BodyInserters.fromFormData("region", "업데이트시")
                        .with("target", target)
                        .with("usage", usage)
                        .with("limit", limitPay))
                .exchange()
                .expectBody(RegionSupportInfoResponseDto.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseDto.getTarget()).isEqualTo(target);
        assertThat(responseDto.getUsage()).isEqualTo(usage);
        assertThat(responseDto.getLimit()).isEqualTo(limitPay);
    }
}