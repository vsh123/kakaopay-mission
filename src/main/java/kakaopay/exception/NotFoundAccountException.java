package kakaopay.exception;

public class NotFoundAccountException extends RuntimeException {
    public NotFoundAccountException() {
        super("유저를 찾을 수 없습니다.");
    }
}
