CREATE TABLE `user`
(
   `id`         bigint(20) NOT NULL AUTO_INCREMENT,
   `name`       VARCHAR(255),
   `username`   VARCHAR(255) NOT NULL,
   `password`   VARCHAR(255) NOT NULL,
   `created_at` datetime     NOT NULL,
   `updated_at` datetime     NOT NULL,
   PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
