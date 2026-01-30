package mvest.core.auth.dto.response;

import mvest.core.user.domain.User;

public record UserTokenDTO(User user, JwtTokenDTO jwtTokenDto) {

    public static UserTokenDTO of(User user, JwtTokenDTO jwtTokenDTO) {
        return new UserTokenDTO(user, jwtTokenDTO);
    }
}
