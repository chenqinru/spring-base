CREATE TABLE `ez_user_role`
(
    `id`      int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id` int unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
    `role_id` int unsigned NOT NULL DEFAULT '0' COMMENT '角色ID',
    PRIMARY KEY (`id`),
    KEY `user_id` (`user_id`),
    KEY `role_id` (`role_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户和角色的中间表';

INSERT INTO `ez_user_role`
VALUES (1, 1, 1);
