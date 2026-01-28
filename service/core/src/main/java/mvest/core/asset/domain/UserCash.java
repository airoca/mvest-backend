package mvest.core.asset.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UserCash {

    private final Long userId;
    private final BigDecimal cashBalance;
    private final LocalDateTime updatedAt;

    public UserCash(Long userId,
                    BigDecimal cashBalance,
                    LocalDateTime updatedAt) {
        this.userId = userId;
        this.cashBalance = cashBalance;
        this.updatedAt = updatedAt;
    }

    public Long getUserId() {
        return userId;
    }

    public BigDecimal getCashBalance() {
        return cashBalance;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
