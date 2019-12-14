package kakaopay.exception;

public class ParsingLimitException extends RuntimeException {
    public ParsingLimitException() {
        super("입력이 잘못되었습니다. 다시한번 확인해 주세요.");
    }
}
