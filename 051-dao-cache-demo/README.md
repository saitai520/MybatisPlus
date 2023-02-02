## MybatisPlus 分布式缓存
### DAO层分布式缓存演示demo

#### 1、添加依赖

```xml
<!--抢先测试版本1.6.1.1 发布版本1.6.2-->
<dependency>
    <groupId>xin.altitude.cms</groupId>
    <artifactId>ucode-cms-common</artifactId>
    <version>1.6.1.1</version>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

#### 2、配置Redis

```yml
spring:
  # redis 配置
  redis:
    # 地址
    host: localhost
    # 端口，默认为6379
    port: 6379
    # 数据库索引
    database: 0
    # 密码
    password:
```

#### 3、增强类

`PlusUtils`、`CacheBaseMapper`、`IServiceCache`三个类

---

#### 4、实战开发
```java

/**
     * 演示一：Service无缓存（二选一）
     */
    @GetMapping(value = "/detail1/{userId}")
    public AjaxResult detail1(@PathVariable("userId") Long userId) {
        return AjaxResult.success(userService.getById(userId));
    }
    
    /**
     * 演示二：Service有缓存（二选一）
     */
    @GetMapping(value = "/detail2/{userId}")
    public AjaxResult detail2(@PathVariable("userId") Long userId) {
        return AjaxResult.success(userService.getById(userId, User.class));
    }
    
    /**
     * 演示三：Mapper无缓存（二选一）
     */
    @GetMapping(value = "/detail3/{userId}")
    public AjaxResult detail3(@PathVariable("userId") Long userId) {
        return AjaxResult.success(userMapper.selectById(userId));
    }
    
    /**
     * 演示四：Mapper有缓存（二选一）
     */
    @GetMapping(value = "/detail4/{userId}")
    public AjaxResult detail4(@PathVariable("userId") Long userId) {
        return AjaxResult.success(userMapper.selectById(userId,User.class));
    }
    
    /**
     * 演示五：Service 批量无缓存（二选一）
     */
    @GetMapping(value = "/detail5/{userId}")
    public AjaxResult detail5(@PathVariable("userId") Long[] userId) {
        List<Long> userIds = Arrays.asList(userId);
        return AjaxResult.success(userService.listByIds(userIds));
    }
    
    /**
     * 演示六：Service 批量有缓存（二选一）
     */
    @GetMapping(value = "/detail6/{userId}")
    public AjaxResult detail6(@PathVariable("userId") Long[] userId) {
        List<Long> userIds = Arrays.asList(userId);
        return AjaxResult.success(userService.listByIds(userIds,User.class));
    }
    
    /**
     * 演示七：Service 批量有缓存 过期时间（二选一）
     */
    @GetMapping(value = "/detail7/{userId}")
    public AjaxResult detail7(@PathVariable("userId") Long[] userId) {
        List<Long> userIds = Arrays.asList(userId);
        return AjaxResult.success(userService.listByIds(userIds,10000L));
    }
```