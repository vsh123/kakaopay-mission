package kakaopay.utils;

import kakaopay.exception.ParsingLimitException;

public class LimitParser {
    private static final String RECOMMEND_REGEX = "추천금액\\s*이내";
    private static final String MILLION_REGEX = "[0-9]+백만원\\s*이내";
    private static final String BILLION_REGEX = "[0-9]+억원\\s*이내";
    private static final String NOT_NUMBER_REGEX = "[^0-9]";
    private static final Long MILLION = 1000000L;
    private static final Long BILLION = 100000000L;


    public static Long parse(String limitSupport) {
        if (limitSupport.matches(RECOMMEND_REGEX)) {
            return 0L;
        }
        if (limitSupport.matches(MILLION_REGEX)) {
            String limitPay = limitSupport.replaceAll(NOT_NUMBER_REGEX, "");
            return Long.parseLong(limitPay) * MILLION;
        }
        if (limitSupport.matches(BILLION_REGEX)) {
            String limitPay = limitSupport.replaceAll(NOT_NUMBER_REGEX, "");
            return Long.parseLong(limitPay) * BILLION;
        }
        throw new ParsingLimitException();
    }
}
