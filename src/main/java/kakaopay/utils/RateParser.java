package kakaopay.utils;

import kakaopay.domain.Rate;
import kakaopay.exception.ParsingRateException;

public class RateParser {
    private static final String ALL_RATE = "대출이자\\s*전액";
    private static final String SINGLE_RATE_REGEX = "[0-9]+.?[0-9]*%?";
    private static final String RANGE_RATE_REGEX = "[0-9]+.?[0-9]*%?~[0-9]+.?[0-9]*%?";

    public static Rate parse(String rateInfo) {
        if (rateInfo.matches(ALL_RATE)) {
            return new Rate(rateInfo, 0, 0);
        }
        if (rateInfo.matches(SINGLE_RATE_REGEX)) {
            double rate = Double.parseDouble(rateInfo.replace("%", ""));
            return new Rate(rateInfo, rate, rate);
        }
        if (rateInfo.matches(RANGE_RATE_REGEX)) {
            String[] rates = rateInfo.replaceAll("%", "").split("~");
            double minRate = Double.parseDouble(rates[0]);
            double maxRate = Double.parseDouble(rates[1]);
            return new Rate(rateInfo, minRate, maxRate);
        }
        throw new ParsingRateException();
    }
}
