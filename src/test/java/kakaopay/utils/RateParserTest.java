package kakaopay.utils;

import kakaopay.domain.Rate;
import kakaopay.exception.ParsingRateException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RateParserTest {

    @Test
    void single_rate_test() {
        String rateInfo = "3%";
        Rate rate = RateParser.parse(rateInfo);

        assertThat(rate.getRate()).isEqualTo(rateInfo);
        assertThat(rate.getMinRate()).isEqualTo(3.0);
        assertThat(rate.getMaxRate()).isEqualTo(3.0);
    }

    @Test
    void range_rate_test() {
        String rateInfo = "3%~5%";
        Rate rate = RateParser.parse(rateInfo);

        assertThat(rate.getRate()).isEqualTo(rateInfo);
        assertThat(rate.getMinRate()).isEqualTo(3.0);
        assertThat(rate.getMaxRate()).isEqualTo(5.0);
    }

    @Test
    void Illegal_rate_test() {
        String rateInfo = "3% ~ asdfasdf%";
        assertThrows(ParsingRateException.class, () -> RateParser.parse(rateInfo));
    }
}