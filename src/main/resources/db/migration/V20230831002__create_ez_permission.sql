CREATE TABLE `ez_permission`
(
    `id`          int unsigned                                                 NOT NULL AUTO_INCREMENT COMMENT '主键',
    `code`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '权限代码',
    `name`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '权限名称',
    `parent_id`   int unsigned                                                 NOT NULL DEFAULT '0' COMMENT '父级主键',
    `create_time` datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `code` (`code`),
    KEY `parent_id` (`parent_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='权限表';

INSERT INTO `ez_permission`
VALUES (1,	'admin:user:list','后台用户列表',0,'2023-07-25 20:54:09','2023-07-25 20:54:09');
