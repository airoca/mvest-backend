package mvest.core.asset.infrastructure;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(
        name = "user_assets",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "stock_code"})
        }
)
public class UserAssetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String stockCode;
    private int quantity;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    public UserAssetEntity(Long id,
                           Long userId,
                           String stockCode,
                           int quantity) {
        this.id = id;
        this.userId = userId;
        this.stockCode = stockCode;
        this.quantity = quantity;
    }
}
