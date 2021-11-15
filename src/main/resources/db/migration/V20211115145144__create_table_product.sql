CREATE TABLE product
(
   `id`         bigint(20) NOT NULL auto_increment comment 'id',
   `title`      VARCHAR(200) NOT NULL comment '标题',
   `desc`       VARCHAR(200) comment '描述',
   `saleable`   boolean      NOT NULL comment '是否上架',
   `created_at` datetime     NOT NULL comment '创建时间',
   `updated_at` datetime     NOT NULL comment '更新时间',
   PRIMARY KEY (id)
);

ALTER TABLE product comment '商品表';
