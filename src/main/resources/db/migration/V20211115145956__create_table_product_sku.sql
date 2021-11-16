CREATE TABLE `product_sku`
(
   `id`         bigint(20) NOT NULL auto_increment comment 'id',
   `product_id` bigint(20) NOT NULL comment 'product id',
   `price`      INT          NOT NULL default 0 comment '价格 分为单位',
   `title`      VARCHAR(200) NOT NULL comment '标题',
   `images`     json comment '图片地址',
   `param`      json comment '商品参数',
   `saleable`   boolean      NOT NULL comment '是否上架',
   `created_at` datetime     NOT NULL comment '创建时间',
   `updated_at` datetime     NOT NULL comment '更新时间',
   PRIMARY KEY (`id`)
);

ALTER TABLE `product_sku` comment 'product sku表';
