CREATE TABLE `ez_role`
(
    `id`          int unsigned                                                  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `code`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '代码',
    `name`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '名称',
    `weight`      tinyint unsigned                                              NOT NULL DEFAULT '0' COMMENT '权重',
    `remark`      varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '备注',
    `create_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `code` (`code`),
    KEY `weight` (`weight`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='角色表';

INSERT INTO `ez_role`
VALUES (1, 'ADMIN', '超级管理员', 1, '', '2023-07-22 00:44:01', '2023-08-31 23:12:25');
