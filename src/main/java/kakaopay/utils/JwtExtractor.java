package kakaopay.utils;

import kakaopay.exception.InvalidJwtException;
import org.springframework.util.StringUtils;

public class JwtExtractor {
    public static final String PREFIX = "Bearer ";

    public static String extract(String header) {
        if (StringUtils.isEmpty(header)) {
            throw new InvalidJwtException();
        }
        return header.substring(PREFIX.length());
    }
}
