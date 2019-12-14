package kakaopay.controller;

import kakaopay.dto.RegionSupportInfoResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.reactive.function.BodyInserters;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;

class FileUploadControllerTest extends CommonControllerTest {
    @Test
    void csv파일_파싱_테스트() {
        File file = new File("src/test/resources/test.csv");
        List<RegionSupportInfoResponseDto> responseDtos = webTestClient.post()
                .uri("/api/upload")
                .contentType(MULTIPART_FORM_DATA)
                .header(AUTHRIZATION_HEADER, PREFIX + token)
                .body(BodyInserters.fromMultipartData("file", new FileSystemResource(file)))
                .exchange()
                .expectBodyList(RegionSupportInfoResponseDto.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseDtos.size()).isEqualTo(1);
        assertThat(responseDtos.get(0).getRegion()).isEqualTo("test");
    }
}