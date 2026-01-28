CREATE TABLE user_assets (
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    stock_code VARCHAR(255),
    quantity INT,
    updated_at DATETIME,
    PRIMARY KEY (id),
    UNIQUE KEY uk_user_stock (user_id, stock_code),
    CONSTRAINT fk_user_assets_user
        FOREIGN KEY (user_id) REFERENCES users(user_id)
);
