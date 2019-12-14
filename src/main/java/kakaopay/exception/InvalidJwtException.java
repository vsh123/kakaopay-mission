package kakaopay.exception;

public class InvalidJwtException extends RuntimeException {
    public InvalidJwtException() {
        super("올바르지 않은 토큰입니다.");
    }
}
