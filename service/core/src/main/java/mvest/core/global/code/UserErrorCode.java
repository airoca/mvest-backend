package mvest.core.global.code;

import org.springframework.http.HttpStatus;

public enum UserErrorCode implements ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저를 찾을 수 없습니다."),
    PLATFORM_USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 플랫폼 유저를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String errorMessage;

    UserErrorCode(HttpStatus httpStatus, String errorMessage) {
        this.status = httpStatus;
        this.errorMessage = errorMessage;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
