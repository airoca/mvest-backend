package mvest.core.user.application;

import mvest.core.auth.dto.PlatformUserDTO;
import mvest.core.auth.dto.request.UserSignupDTO;
import mvest.core.auth.dto.response.JwtTokenDTO;
import mvest.core.user.domain.User;

public interface UserRepository {
    User create(PlatformUserDTO platformUserDTO, UserSignupDTO userSignupDTO);
    void saveToken(Long userId, JwtTokenDTO token);
}
