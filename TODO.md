# 踩坑日记

## Spring Boot

1. request请求对象或response响应对象，对象中的Stream流可以使用包装器复制，解决整个线程只能获取一次的问题，可以让程序重复获取。
2. stream流只能被访问一次，访问之后就会关闭。
3. @Validated验证器是在进入控制器方法前执行的，当验证失败时，aop日志切面无法切入。
4. 生命周期执行顺序：filter -> intecepter -> controller -> ...
5. @JsonIgnoreProperties可以忽略类中的某个字段。

## Spring Data Jpa

1. @Transient JPA将在进行数据库交互时忽略这个字段。
2. @DynamicInsert JPA将在新增时忽略值为NULL的字段。
3. @DynamicUpdate 只更新发生变化的字段，而忽略未修改的字段，还有忽略值为NULL的字段。
4. FetchType.LAZY在使用懒加载时，他的触发机制是只要访问级联属性时就会触发。
5. 在配置FetchType.LAZY时会报错：报错：org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: com.xxx.xxx.xxx, could not initialize proxy - no Session
```
方案1:配置FetchType.EAGER
方案2:在方法上面添加事务，使得session等到该方法执行结束后才失效。@Transactional(readOnly = true)
方案3:全局配置jpa.properties.hibernate.enable_lazy_load_no_trans = true
```
6. 使用cascade = {CascadeType.PERSIST, CascadeType.MERGE}属性，会出现级联更新时自动更新role表的情况，不配置就不会出现。
7. 双向配置@JsonManagedReference和@JsonBackReference可以解决嵌套加载的问题，类似在反向级联时配置@JsonIgnore解决嵌套加载的问题。
8. @Fetch(FetchMode.SUBSELECT) 将把主表的查询语句复制一份到从表的查询语句中进行子查询。
9. @BatchSize(size = 100) 将主表的结果集id放到从表的查询语句中进行子查询（目前看最完美）。
10. Specification可以构建复杂的查询语句，Example只能构建简单的查询语句。
11. 主表在Specification中配置root.fetch("级联字段", JoinType.LEFT)时，将会把所有的级联关系汇总在一次查询中完成，并且不会分页。
12. 在Repository中配置@EntityGraph(attributePaths = "级联字段")时，将会把所有的级联关系汇总在一次查询中完成，并且不会分页。
13. 在更新数据前如果使用过findById方法，底层将不会执行查询操作，这可能就是Repository持久层的作用。按照此特性，就拥有了很多可扩展性。
14. 如果有场景需要只更新主实体，而不更新级联属性，那么需要先查询数据库，然后将数据赋值到需要更新的级联属性上，底层会判断如果级联属性相同则不会触发级联的操作。

## Spring Security
1. 在Spring Boot 2.7.0之后，SpringSecurity 5.4.x采用了新用法配置，写法有些不同。
2. 如果用了@PreAuthorize注解，包括其他的注解，会以注解为准，忽略SecurityConfig中配置的放行地址，包括配置的exceptionHandling自定义未授权和未登录结果。
3. @Validated比@PreAuthorize先执行，采用自定义拦截器方案解决。
4. 在SecurityConfig配置的过滤器链中，比如JwtAuthenticationTokenFilter类，在类中抛出的异常无法被注册的@RestControllerAdvice类接管。
5. 在JwtAuthenticationTokenFilter抛出异常时，记录日志拦截器无法获取request对象。

## 其他
1. knife4j的增强注解@ApiOperationSupport(ignoreParameters = {"id"})忽略字段，存在会忽略其他字段的BUG，官方建议使用includeParameters属性或者新增一个类区分开。
2. flyway中的前缀：V 表示版本化迁移，U 表示撤销迁移，R 表示可重复迁移
3. flyway.baseline-version：第一次运行时会执行所有脚本，当删除flyway_schema_history表后，会跳过20230831006和之前的脚本


