CREATE TABLE `ez_role_permission`
(
    `id`            int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `role_id`       int unsigned NOT NULL DEFAULT '0' COMMENT '角色ID',
    `permission_id` int unsigned NOT NULL DEFAULT '0' COMMENT '权限ID',
    PRIMARY KEY (`id`),
    KEY `role_id` (`role_id`),
    KEY `permission_id` (`permission_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='角色和权限的中间表';
