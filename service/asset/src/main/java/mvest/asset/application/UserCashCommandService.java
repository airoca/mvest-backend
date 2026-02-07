package mvest.asset.application;

import lombok.RequiredArgsConstructor;
import mvest.asset.domain.AssetTransaction;
import mvest.asset.domain.UserCash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
class UserCashCommandService {

    private static final BigDecimal INITIAL_CASH = BigDecimal.valueOf(1_000_000);

    private final UserCashRepository userCashRepository;
    private final AssetTransactionRepository assetTransactionRepository;

    @Transactional
    public void createInitialCash(Long userId) {

        // 중복 이벤트 방어 (멱등성)
        if (userCashRepository.existsByUserId(userId)) {
            return;
        }

        UserCash userCash = UserCash.initial(userId, INITIAL_CASH);
        userCashRepository.save(userCash);

        AssetTransaction assetTransaction = AssetTransaction.initialDeposit(userId, INITIAL_CASH);
        assetTransactionRepository.save(assetTransaction);
    }
}
