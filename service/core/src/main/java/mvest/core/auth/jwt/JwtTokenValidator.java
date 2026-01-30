package mvest.core.auth.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import mvest.core.global.code.AuthErrorCode;
import mvest.core.global.exception.AuthException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenValidator {

    private final JwtTokenProvider jwtTokenProvider;

    public void validateAccessToken(String accessToken) {
        if (accessToken == null) {
            throw new AuthException(AuthErrorCode.EMPTY_TOKEN);
        }
        try {
            boolean isAccessToken = jwtTokenProvider.getClaimFromToken(accessToken).isAccessToken();
            if (!isAccessToken) {
                throw new AuthException(AuthErrorCode.INVALID_TOKEN_TYPE);
            }
        } catch (MalformedJwtException ex) {
            throw new AuthException(AuthErrorCode.INVALID_TOKEN);
        } catch (ExpiredJwtException ex) {
            throw new AuthException(AuthErrorCode.EXPIRED_TOKEN);
        } catch (UnsupportedJwtException ex) {
            throw new AuthException(AuthErrorCode.UNSUPPORTED_TOKEN);
        } catch (IllegalArgumentException ex) {
            throw new AuthException(AuthErrorCode.EMPTY_TOKEN);
        }
    }

    public void validateRefreshToken(String refreshToken) {
        if (refreshToken == null) {
            throw new AuthException(AuthErrorCode.EMPTY_REFRESH_TOKEN);
        }
        try {
            boolean isAccessToken = jwtTokenProvider.getClaimFromToken(refreshToken).isAccessToken();
            if (isAccessToken) {
                throw new AuthException(AuthErrorCode.INVALID_TOKEN_TYPE);
            }
        } catch (MalformedJwtException ex) {
            throw new AuthException(AuthErrorCode.INVALID_REFRESH_TOKEN);
        } catch (ExpiredJwtException ex) {
            throw new AuthException(AuthErrorCode.EXPIRED_REFRESH_TOKEN);
        } catch (UnsupportedJwtException ex) {
            throw new AuthException(AuthErrorCode.UNSUPPORTED_REFRESH_TOKEN);
        } catch (IllegalArgumentException ex) {
            throw new AuthException(AuthErrorCode.EMPTY_REFRESH_TOKEN);
        }
    }
}
