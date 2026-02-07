package mvest.asset.infrastructure;

import lombok.RequiredArgsConstructor;
import mvest.asset.domain.UserCash;
import mvest.asset.application.UserCashRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserCashRepositoryImpl implements UserCashRepository {

    private final UserCashJpaRepository userCashJpaRepository;

    @Override
    public void save(UserCash userCash) {
        UserCashEntity entity = UserCashMapper.toEntity(userCash);
        userCashJpaRepository.save(entity);
    }

    @Override
    public boolean existsByUserId(Long userId) {
        return userCashJpaRepository.existsById(userId);
    }
}
