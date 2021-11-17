CREATE TABLE `orders_detail`
(
   `order_id`     bigint(20) not null comment 'product id',
   `sku_id`       bigint(20) not null comment 'sku id',
   `price`        int      not null comment '原价',
   `actual_price` int      not null comment '实际购买价格',
   `num`          int      not null default 0 comment '购买数量',
   `created_at`   datetime not null comment '创建时间',
   `updated_at`   datetime not null comment '更新时间',
   primary key (order_id, sku_id)
);

alter table orders_detail comment '订单详情表';
