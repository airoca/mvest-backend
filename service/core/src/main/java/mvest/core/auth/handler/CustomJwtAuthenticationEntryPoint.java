package mvest.core.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import mvest.core.global.code.AuthErrorCode;
import mvest.core.global.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class CustomJwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        Object exceptionMessage = request.getAttribute("exception");
        if (exceptionMessage instanceof AuthErrorCode authErrorCode) {
            setResponse(response, authErrorCode);
        } else {
            setResponse(response, AuthErrorCode.UNAUTHORIZED);
        }
    }

    private void setResponse(HttpServletResponse response, AuthErrorCode errorMessage) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(
                objectMapper.writeValueAsString(ResponseDTO.failure(errorMessage))
        );
    }
}
