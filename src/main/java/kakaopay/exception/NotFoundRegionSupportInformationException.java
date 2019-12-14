package kakaopay.exception;

public class NotFoundRegionSupportInformationException extends RuntimeException {
    public NotFoundRegionSupportInformationException() {
        super("해당 지자체가 지원하는 정보를 찾을 수 없습니다.");
    }
}
