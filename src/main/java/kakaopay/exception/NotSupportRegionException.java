package kakaopay.exception;

public class NotSupportRegionException extends RuntimeException {
    public NotSupportRegionException() {
        super("지원하지 않는 지자체명 입니다.");
    }
}
