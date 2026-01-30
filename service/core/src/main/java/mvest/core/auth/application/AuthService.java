package mvest.core.auth.application;

import lombok.RequiredArgsConstructor;
import mvest.core.auth.dto.PlatformUserDTO;
import mvest.core.auth.dto.request.PlatformRequestDTO;
import mvest.core.auth.dto.request.UserSignupDTO;
import mvest.core.auth.dto.response.JwtTokenDTO;
import mvest.core.auth.dto.response.LoginResponseDTO;
import mvest.core.auth.dto.response.UserTokenDTO;
import mvest.core.auth.jwt.JwtTokenProvider;
import mvest.core.auth.jwt.JwtTokenValidator;
import mvest.core.global.code.AuthErrorCode;
import mvest.core.global.exception.AuthException;
import mvest.core.user.application.UserRepository;
import mvest.core.user.domain.Platform;
import mvest.core.user.domain.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtTokenValidator jwtTokenValidator;
    private final KakaoService kakaoService;

    public UserTokenDTO signup(String platformToken, UserSignupDTO userSignupDTO) {
        PlatformUserDTO platformUserDTO = getPlatformInfo(platformToken, userSignupDTO);
        User user = userRepository.create(platformUserDTO, userSignupDTO);
        JwtTokenDTO token = jwtTokenProvider.generateTokenPair(user.getUserId());
        userRepository.saveToken(user.getUserId(), token);
        return UserTokenDTO.of(user, token);
    }

    public LoginResponseDTO login(PlatformRequestDTO platformRequestDTO, String platformToken) {
        return null;
    }

    public void logout(Long userId) {
    }

    public JwtTokenDTO reissue(String refreshToken) {
        return null;
    }

    public void withdraw(Long userId) {
    }

    // signup
    private PlatformUserDTO getPlatformInfo(String platformToken, UserSignupDTO userSignupDto) {
        if (userSignupDto.platform().toString().equals("KAKAO")){
            return kakaoService.getPlatformUserInfo(platformToken);
        } else {
            throw new AuthException(AuthErrorCode.PLATFORM_NOT_FOUND);
        }
    }

    // login
    private PlatformUserDTO getPlatformInfo(Platform platform, String platformToken) {
        if (platform.toString().equals("KAKAO")){
            return kakaoService.getPlatformUserInfo(platformToken);
        } else {
            throw new AuthException(AuthErrorCode.PLATFORM_NOT_FOUND);
        }
    }
}
