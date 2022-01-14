package joy.world.fc.finalproject.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    PASSWORD_NOT_MATCH("아이디 혹은 비밀번호가 일치하지 않습니다."),
    ALREADY_EXISTS_USER("중복된 이메일입니다."),
    USER_NOT_FOUND("존재하지 않는 계정입니다."),
    VALIDATION_FAIL("잘못된 값입니다."),
    BAD_REQUEST("잘못된 접근입니다."),
    EVENT_CREATE_OVERLAPPED_PERIOD("이벤트기간이 중복 됩니다.");

    private final String message;

}
