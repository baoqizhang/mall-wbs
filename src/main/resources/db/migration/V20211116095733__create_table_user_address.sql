create table `user_address`
(
   `id`         bigint(20) not null auto_increment comment 'id',
   `user_id`    bigint(20) not null comment 'user id',
   `name`       varchar(200) not null comment '收件人姓名',
   `phone`      char(11) comment '收货人手机号',
   `prime`      boolean      not null default false comment '是否作为默认地址',
   `address`    varchar(200) not null comment '收获地址',
   `created_at` datetime     not null comment '创建时间',
   `updated_at` datetime     not null comment '更新时间',
   primary key (`id`)
);

alter table `user_address` comment '用户地址表';
