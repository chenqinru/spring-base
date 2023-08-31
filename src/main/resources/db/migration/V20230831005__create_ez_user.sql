CREATE TABLE `ez_user`
(
    `id`          int unsigned                                                  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `username`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '账号',
    `password`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
    `nickname`    varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '昵称',
    `birthday`    date                                                                   DEFAULT NULL COMMENT '生日',
    `status`      tinyint unsigned                                              NOT NULL DEFAULT '0' COMMENT '状态 ',
    `create_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `username` (`username`),
    KEY `status` (`status`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户表';

INSERT INTO `ez_user` VALUES (1,'admin','$2a$10$eJ6aceoKVohXCn3D9ET17.oaT2arhHY2FLNbAKUywj58Jn.t7lcUO','超级管理员','2023-07-10',0,'2023-07-10 21:27:06','2023-07-22 22:25:00');
