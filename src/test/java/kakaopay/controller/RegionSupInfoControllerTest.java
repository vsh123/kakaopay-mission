package kakaopay.controller;

import kakaopay.dto.RegionNameResponseDto;
import kakaopay.dto.RegionSupportInfoResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RegionSupInfoControllerTest extends CommonControllerTest {

    public static final String BASE_URI = "/api/regionsupinfos";


    @Test
    void findTopOf() {
        List<RegionNameResponseDto> result = webTestClient.get()
                .uri(BASE_URI + "/rank?k=2")
                .header(AUTHRIZATION_HEADER, PREFIX + token)
                .exchange()
                .expectBodyList(RegionNameResponseDto.class)
                .returnResult()
                .getResponseBody();

        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void findMinRate() {
        RegionNameResponseDto result = webTestClient.get()
                .uri(BASE_URI + "/min")
                .header(AUTHRIZATION_HEADER, PREFIX + token)
                .exchange()
                .expectBody(RegionNameResponseDto.class)
                .returnResult()
                .getResponseBody();


        assertThat(result.getRegion()).isEqualTo("11");
    }

    @Test
    void updateTest() {
        String target = "updateTarget";
        String usage = "updateUsage";
        String limitPay = "1억원 이내";

        RegionSupportInfoResponseDto responseDto = webTestClient.put()
                .uri(BASE_URI)
                .header(AUTHRIZATION_HEADER, PREFIX + token)
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