package kakaopay.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LimitParserTest {
    @Test
    void 지원금액_이내() {
        Long result = LimitParser.parse("추천금액 이내");
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void Million() {
        Long result = LimitParser.parse("30백만원 이내");
        assertThat(result).isEqualTo(30000000L);
    }

    @Test
    void Billion() {
        Long result = LimitParser.parse("1억원 이내");
        assertThat(result).isEqualTo(100000000L);
    }
}