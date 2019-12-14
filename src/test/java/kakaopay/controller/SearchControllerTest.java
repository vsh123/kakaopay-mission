package kakaopay.controller;

import kakaopay.dto.RegionSupportInfoResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SearchControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void findByRegionName() {
        RegionSupportInfoResponseDto responseDto = webTestClient.get()
                .uri("/api/regionsupinfos/강릉시")
                .exchange()
                .expectBody(RegionSupportInfoResponseDto.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseDto.getRegion()).isEqualTo("강릉시");
        assertThat(responseDto.getMgmt()).isEqualTo("mgmt");
    }
}