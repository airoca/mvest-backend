CREATE TABLE orders (
    id BIGINT NOT NULL AUTO_INCREMENT,
    order_id BINARY(16) NOT NULL,
    user_id BIGINT,
    stock_code VARCHAR(255),
    order_type VARCHAR(255),
    price DECIMAL(19, 4),
    quantity INT,
    status VARCHAR(255),
    created_at DATETIME,
    updated_at DATETIME,
    PRIMARY KEY (id),
    UNIQUE KEY uk_orders_order_id (order_id)
);
