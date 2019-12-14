package kakaopay.controller;

import kakaopay.dto.RegionSupportInfoResponseDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SearchControllerTest extends CommonControllerTest {

    @Test
    void findByRegionName() {
        RegionSupportInfoResponseDto responseDto = webTestClient.get()
                .uri("/api/regionsupinfos/search?region=강릉시")
                .exchange()
                .expectBody(RegionSupportInfoResponseDto.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseDto.getRegion()).isEqualTo("강릉시");
        assertThat(responseDto.getMgmt()).isEqualTo("mgmt");
    }
}