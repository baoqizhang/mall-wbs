CREATE TABLE `orders`
(
   `id`           bigint(20) not null auto_increment comment 'id',
   `order_no`     varchar(200) not null comment '订单号',
   `amount`       int          not null default 0 comment '订单总金额 单位分',
   `actual_price` int          not null default 0 comment '订单实际支付金额',
   `status`       varchar(64)  not null comment '状态：未付款、已付款、已发货、已签收',
   `address_id`   bigint(20) not null comment '收货地址id',
   `created_at`   datetime     not null comment '创建时间',
   `updated_at`   datetime     not null comment '更新时间',
   primary key (id)
);

alter table `orders` comment '订单表';
