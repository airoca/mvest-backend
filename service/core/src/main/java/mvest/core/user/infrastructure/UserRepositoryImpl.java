package mvest.core.user.infrastructure;

import lombok.RequiredArgsConstructor;
import mvest.core.auth.dto.PlatformUserDTO;
import mvest.core.auth.dto.request.UserSignupDTO;
import mvest.core.auth.dto.response.JwtTokenDTO;
import mvest.core.auth.infrastructure.TokenEntity;
import mvest.core.auth.infrastructure.TokenRedisRepository;
import mvest.core.global.code.UserErrorCode;
import mvest.core.global.exception.BusinessException;
import mvest.core.user.application.UserRepository;
import mvest.core.user.domain.User;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final TokenRedisRepository tokenRedisRepository;

    @Override
    public User create(PlatformUserDTO platformUserDTO, UserSignupDTO userSignupDTO) {
        UserEntity userEntity = userJpaRepository.findByPlatformAndPlatformId(userSignupDTO.platform(), platformUserDTO.platformId())
                .orElse(null);

        // 이미 가입된 아이디
        if (userEntity != null) {
            return UserMapper.toDomain(userEntity);
        }

        userEntity = UserEntity.builder()
                .platform(userSignupDTO.platform())
                .platformId(platformUserDTO.platformId())
                .userName(userSignupDTO.userName())
                .birth(userSignupDTO.birth())
                .build();

        userJpaRepository.save(userEntity);

        return userJpaRepository.findByPlatformAndPlatformId(userSignupDTO.platform(), platformUserDTO.platformId())
                .map(UserMapper::toDomain)
                .orElseThrow(() -> new BusinessException(UserErrorCode.PLATFORM_USER_NOT_FOUND));
    }

    @Override
    public void saveToken(Long userId, JwtTokenDTO token) {
        System.out.println("저장된 refresh token: " + token.refreshToken());
        TokenEntity tokenEntity = TokenEntity.builder()
                .id(userId)
                .refreshToken(token.refreshToken())
                .build();
        tokenRedisRepository.save(tokenEntity);
    }
}
