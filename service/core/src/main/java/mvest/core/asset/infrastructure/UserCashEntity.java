package mvest.core.asset.infrastructure;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_cash")
public class UserCashEntity {

    @Id
    private Long userId;

    private BigDecimal cashBalance;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    public UserCashEntity(Long userId,
                          BigDecimal cashBalance) {
        this.userId = userId;
        this.cashBalance = cashBalance;
    }
}
