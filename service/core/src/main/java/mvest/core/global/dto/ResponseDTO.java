package mvest.core.global.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import mvest.core.global.code.ErrorCode;
import mvest.core.global.code.SuccessCode;

public record ResponseDTO<T>(
        int code,
        String message,
        @JsonInclude(value = JsonInclude.Include.NON_NULL)
        T data
) {
    // response body가 필요하지 않은 경우
    public static <T> ResponseDTO<T> success(SuccessCode successCode) {
        return new ResponseDTO<>(successCode.getStatus().value(), successCode.getMessage(), null);
    }

    // response body가 필요한 경우
    public static <T> ResponseDTO<T> success(SuccessCode successCode, T data) {
        return new ResponseDTO<>(successCode.getStatus().value(), successCode.getMessage(), data);
    }

    public static <T> ResponseDTO<T> failure(ErrorCode failureCode) {
        return new ResponseDTO<>(failureCode.getStatus().value(), failureCode.getMessage(), null);
    }
}
