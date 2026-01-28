package mvest.core.asset.domain;

import java.time.LocalDateTime;

public class UserAsset {

    private final Long id;
    private final Long userId;
    private final String stockCode;
    private final int quantity;
    private final LocalDateTime updatedAt;

    public UserAsset(Long id,
                     Long userId,
                     String stockCode,
                     int quantity,
                     LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.stockCode = stockCode;
        this.quantity = quantity;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
