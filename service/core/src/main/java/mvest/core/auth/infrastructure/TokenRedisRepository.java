package mvest.core.auth.infrastructure;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TokenRedisRepository extends CrudRepository<TokenEntity, Long> {
    Optional<TokenEntity> findByRefreshToken(String refreshToken);
    void deleteByRefreshToken(String refreshToken);
}
