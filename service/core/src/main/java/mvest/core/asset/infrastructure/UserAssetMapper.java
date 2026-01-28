package mvest.core.asset.infrastructure;

import mvest.core.asset.domain.UserAsset;

public final class UserAssetMapper {

    public static UserAsset toDomain(UserAssetEntity entity) {
        return new UserAsset(
                entity.getId(),
                entity.getUserId(),
                entity.getStockCode(),
                entity.getQuantity(),
                entity.getUpdatedAt()
        );
    }

    public static UserAssetEntity toEntity(UserAsset domain) {
        return UserAssetEntity.builder()
                .id(domain.getId())
                .userId(domain.getUserId())
                .stockCode(domain.getStockCode())
                .quantity(domain.getQuantity())
                .build();
    }
}
