package kakaopay.exception;

public class AlreadyExistUserIdException extends RuntimeException {
    public AlreadyExistUserIdException() {
        super("이미 존재하는 유저 아이디입니다.");
    }
}
