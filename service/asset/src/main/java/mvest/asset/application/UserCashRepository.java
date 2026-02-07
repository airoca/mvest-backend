package mvest.asset.application;

import mvest.asset.domain.UserCash;

public interface UserCashRepository {
    void save(UserCash userCash);
    boolean existsByUserId(Long userId);
}
