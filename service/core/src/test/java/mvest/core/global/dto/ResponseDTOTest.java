package mvest.core.global.dto;

import mvest.core.global.code.CommonErrorCode;
import mvest.core.global.code.CommonSuccessCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class ResponseDTOTest {

    @Test
    @DisplayName("성공 응답 생성 - 데이터 없음")
    void successWithoutData() {
        // when
        ResponseDTO<Void> response = ResponseDTO.success(CommonSuccessCode.OK);

        // then
        assertThat(response.code()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.message()).isEqualTo("요청이 성공했습니다.");
        assertThat(response.data()).isNull();
    }

    @Test
    @DisplayName("성공 응답 생성 - 데이터 포함")
    void successWithData() {
        // given
        String testData = "test data";

        // when
        ResponseDTO<String> response = ResponseDTO.success(CommonSuccessCode.OK, testData);

        // then
        assertThat(response.code()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.message()).isEqualTo("요청이 성공했습니다.");
        assertThat(response.data()).isEqualTo(testData);
    }

    @Test
    @DisplayName("실패 응답 생성 - INTERNAL_SERVER_ERROR")
    void failureInternalServerError() {
        // when
        ResponseDTO<Void> response = ResponseDTO.failure(CommonErrorCode.INTERNAL_SERVER_ERROR);

        // then
        assertThat(response.code()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.message()).isEqualTo("서버 내부 오류입니다.");
        assertThat(response.data()).isNull();
    }
}
