package kakaopay.exception;

public class JwtCreateException extends RuntimeException{
    public JwtCreateException() {
        super("토근 생성에 실패하였습니다.");
    }
}
