drop table if exists restaurant_approval_outbox_message_entity;
create table restaurant_approval_outbox_message_entity (
    user_id bigint,
    id binary(16) not null,
    restaurant_id binary(16),
    saga_id binary(16),
    tracking_id binary(16),
    order_status enum ('APPROVED','CANCELLED','PAID','PENDING'),
    outbox_status enum ('COMPLETED','FAILED','STARTED'),
    primary key (id)
) engine=InnoDB;

drop table if exists payment_outbox;
create table payment_outbox (
    total_price decimal(38,2),
    created_at datetime(6),
    user_id bigint,
    version bigint,
    id binary(16) not null,
    saga_id binary(16),
    outbox_status enum ('COMPLETED','FAILED','STARTED'),
    primary key (id)
) engine=InnoDB;

drop table if exists order_item_entity;
create table order_item_entity (
    price decimal(38,2),
    quantity integer,
    sub_total decimal(38,2),
    order_item_id bigint not null,
    order_id binary(16) not null,
    product_id binary(16),
    primary key (order_item_id, order_id)
) engine=InnoDB;

drop table if exists order_entity;
create table order_entity (
    total_price decimal(38,2),
    user_id bigint,
    order_id binary(16) not null,
    restaurant_id binary(16),
    tracking_id binary(16),
    delivery_address varchar(255),
    order_status enum ('APPROVED','CANCELLED','PAID','PENDING'),
    primary key (order_id)
) engine=InnoDB;

drop table if exists order_approval_outbox_message_entity;
create table order_approval_outbox_message_entity (
    user_id bigint,
    version bigint,
    id binary(16) not null,
    order_id binary(16),
    restaurant_id binary(16),
    saga_id binary(16),
    order_status enum ('APPROVED','CANCELLED','PAID','PENDING'),
    outbox_status enum ('COMPLETED','FAILED','STARTED'),
    primary key (id)
) engine=InnoDB;

drop table if exists restaurant;
CREATE TABLE restaurant (
    restaurant_id BINARY(16) NOT NULL,
    product_id BINARY(16) NOT NULL,
    restaurant_active BOOLEAN,
    product_name VARCHAR(255),
    product_price DECIMAL(10, 2),
    product_available BOOLEAN,
    PRIMARY KEY (restaurant_id, product_id)
);

drop view if exists restaurant_view;
CREATE VIEW restaurant_view AS
SELECT
    restaurant_id,
    product_id,
    restaurant_active,
    product_name,
    product_price,
    product_available
FROM
    restaurant;
