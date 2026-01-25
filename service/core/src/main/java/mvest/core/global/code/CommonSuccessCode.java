package mvest.core.global.code;

import org.springframework.http.HttpStatus;

public enum CommonSuccessCode implements SuccessCode {
    OK(HttpStatus.OK, "요청이 성공했습니다."),
    CREATED(HttpStatus.CREATED, "리소스가 생성되었습니다.");

    private final HttpStatus status;
    private final String successMessage;

    CommonSuccessCode(HttpStatus httpStatus, String successMessage) {
        this.status = httpStatus;
        this.successMessage = successMessage;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return successMessage;
    }
}
