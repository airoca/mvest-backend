package mvest.core.global.code;

import org.springframework.http.HttpStatus;

public enum AuthErrorCode implements ErrorCode {
    PLATFORM_NOT_FOUND(HttpStatus.BAD_REQUEST, "유효하지 않은 소셜 플랫폼입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증되지 않은 사용자입니다."),

    // JWT (ACCESS)
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다."),
    UNSUPPORTED_TOKEN(HttpStatus.BAD_REQUEST, "지원하지 않는 토큰 형식입니다."),
    EMPTY_TOKEN(HttpStatus.BAD_REQUEST, "토큰이 비어 있거나 잘못된 요청입니다."),

    // JWT (REFRESH)
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 Refresh 토큰입니다."),
    EXPIRED_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "Refresh 토큰이 만료되었습니다."),
    UNSUPPORTED_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "지원하지 않는 Refresh 토큰 형식입니다."),
    EMPTY_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "Refresh 토큰이 비어 있거나 잘못된 요청입니다."),

    // JWT (ACCESS + REFRESH)
    INVALID_TOKEN_TYPE(HttpStatus.BAD_REQUEST, "올바르지 않은 토큰 타입입니다.");

    private final HttpStatus status;
    private final String errorMessage;

    AuthErrorCode(HttpStatus httpStatus, String errorMessage) {
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
