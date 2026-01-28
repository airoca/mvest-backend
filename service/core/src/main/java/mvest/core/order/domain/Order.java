package mvest.core.order.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {

    private final Long id;
    private final Long orderId;
    private final Long userId;
    private final String stockCode;
    private final String orderType;
    private final BigDecimal price;
    private final int quantity;
    private final String status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Order(Long id,
                 Long orderId,
                 Long userId,
                 String stockCode,
                 String orderType,
                 BigDecimal price,
                 int quantity,
                 String status,
                 LocalDateTime createdAt,
                 LocalDateTime updatedAt) {
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.stockCode = stockCode;
        this.orderType = orderType;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public String getOrderType() {
        return orderType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
