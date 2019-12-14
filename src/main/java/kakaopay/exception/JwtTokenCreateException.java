package kakaopay.exception;

public class JwtTokenCreateException extends RuntimeException{
    public JwtTokenCreateException() {
        super("토근 생성에 실패하였습니다.");
    }
}
