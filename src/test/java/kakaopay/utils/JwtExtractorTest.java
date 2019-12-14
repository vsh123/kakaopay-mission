package kakaopay.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JwtExtractorTest {
    @Test
    void extract() {
        String token = "asdf.asdf.asdf";
        String header = JwtExtractor.PREFIX + token;

        String extract = JwtExtractor.extract(header);

        assertThat(extract).isEqualTo(token);
    }
}