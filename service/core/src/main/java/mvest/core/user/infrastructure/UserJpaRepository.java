package mvest.core.user.infrastructure;

import mvest.core.user.domain.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByPlatformAndPlatformId(Platform platform, String platformId);
}
