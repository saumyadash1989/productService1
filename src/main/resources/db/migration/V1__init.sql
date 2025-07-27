CREATE TABLE category
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime NULL,
    name       VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE product
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    created_at  datetime NULL,
    descrption  VARCHAR(255) NULL,
    price DOUBLE NOT NULL,
    category_id BIGINT NULL,
    title       VARCHAR(255) NULL,
    image       VARCHAR(255) NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);