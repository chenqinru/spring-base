# spring-base

spring-base是一个后台管理系统的脚手架项目，可以在本项目的基础上快速开始后端管理平台的开发。

## 本项目的特性
1. 从零开始搭建的开发平台，致力于满足各种快速开发的场景，以简单和高效为主要出发点，不追求过于复杂的架构和技术。
2. 平台支持代码生成，常用的代码均可以通过代码生成器生成，大大减少了编写样本代码的时间。
3. 支持记录系统运行日志、业务操作日志（包括登录、操作、异常等），方便问题的调试。
4. 支持创建定时任务。
5. 在线展示接口文档，接口文档支持接口调试。
6. 支持对数据库连接的监控。
7. 支持基于角色和权限的细粒度的系统鉴权。

## 内置功能模块
1. 用户管理
2. 角色管理权限管理
3. 菜单管理
4. 部门管理
5. 数据字典
6. 系统通知
7. 文件服务
8. 其他常用功能：系统参数配置、在线状态监控


### 技术栈
- Maven 项目理工具
- Spring boot 基础框架
- Spring security 安全框架，用于身份认证和鉴权
- Spring data jpa 持久层框架，底层为hibernate实现
- Spring scheduling 定时任务调度器
- JWT 生成token
- Flyway 数据库迁移(migration)
- knife4j作为接口文档的门面
- alibaba druid作为数据库连接池
- ...