# Simple Mem Cache Spring Boot Starter

[![maven-central](https://img.shields.io/maven-central/v/cc.duduhuo/simple-mem-cache-spring-boot-starter.svg?style=flat)](https://mvnrepository.com/artifact/cc.duduhuo/simple-mem-cache-spring-boot-starter)

用于在 **Spring Boot** 项目中方便集成 [simple-mem-cache](https://github.com/liying2008/simple-mem-cache) 。

`simple-mem-cache` 是一款轻量级、高性能的 **内存缓存工具库**。
支持 TTL（过期时间）、RU 淘汰策略、并发访问与统计信息。
适用于 Web 应用、服务本地缓存、配置字典缓存等轻量场景，提供极简 API，开箱即用。

## 引入方式

- 使用 Maven

```xml

<dependency>
    <groupId>cc.duduhuo</groupId>
    <artifactId>simple-mem-cache-spring-boot-starter</artifactId>
    <version>1.3.1-1</version>
</dependency>
```

- 使用 Gradle (Groovy)

```groovy
implementation 'cc.duduhuo:simple-mem-cache-spring-boot-starter:1.3.1-1'
```

- 使用 Gradle (Kotlin)

```kotlin
implementation("cc.duduhuo:simple-mem-cache-spring-boot-starter:1.3.1-1")
```

## 配置项列表

| 配置项                           | 类型       | 默认值    | 描述                                  |
|-------------------------------|----------|--------|-------------------------------------|
| `simple.cache.max-size`       | Integer  | `0`    | 缓存最大容量，0 表示不限制                      |
| `simple.cache.default-ttl`    | Duration | `0`    | 默认缓存过期时间，0 表示永不过期，精度：毫秒             |
| `simple.cache.auto-clean`     | Boolean  | `true` | 是否启用自动清理线程                          |
| `simple.cache.clean-interval` | Duration | `1m`   | 自动清理间隔（仅在 autoClean=true 时生效），精度：分钟 |
| `simple.cache.listener`       | Class    | `null` | 缓存事件监听器（可选），需实现 CacheListener 接口    |

可以在 `application.properties` 中配置，示例：

```properties
simple.cache.max-size=10000
simple.cache.default-ttl=30m
simple.cache.auto-clean=true
simple.cache.clean-interval=1m
simple.cache.listener=com.example.MySimpleCacheListener
```

## 使用

可以直接在组件中注入 `SimpleCache` 类型的 bean 。

```java
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final SimpleCache<String, BookEntity> simpleCache;

    public BookService(BookRepository bookRepository, SimpleCache<String, BookEntity> simpleCache) {
        this.bookRepository = bookRepository;
        this.simpleCache = simpleCache;
    }

    public BookEntity getBookByCode(String code) {
        return simpleCache.getOrLoad(code, bookRepository::getBookByCode);
    }
}
```

可自定义缓存监听器，示例：

```java
public class MySimpleCacheListener implements CacheListener<Object, Object> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MySimpleCacheListener.class);

    @Override
    public void onPut(Object key, Object value) {
        LOGGER.info("Put [{}] = {}", key, value);
    }

    @Override
    public void onRemove(Object key, Object value, String reason) {
        LOGGER.info("Removed [{}] = {} because {}", key, value, reason);
    }
}
```

## Metrics binding (Actuator)

`simple-mem-cache-spring-boot-starter` 提供了以下 Metrics 绑定，支持将缓存统计信息暴露给 Prometheus 等度量平台。

- `simple.cache.size`：当前有效缓存个数
- `simple.cache.hits`：缓存命中次数
- `simple.cache.misses`：缓存未命中次数
- `simple.cache.evictions`：缓存清理次数

### 开启方法

1. 添加依赖：

```gradle
implementation("org.springframework.boot:spring-boot-starter-actuator")
```

2. 添加配置：

在 `application.properties` 中添加以下配置：

```properties
management.endpoints.web.exposure.include=metrics
```

3. 访问指定端点：

访问 `/actuator/metrics/simple.cache.*` 即可获取统计信息。如 `/actuator/metrics/simple.cache.size` 。

输出示例：

```json
{
  "name": "simple.cache.size",
  "description": "The number of valid entries in the cache",
  "baseUnit": "entries",
  "measurements": [
    {
      "statistic": "VALUE",
      "value": 2
    }
  ],
  "availableTags": []
}
```

## 版本要求

- Java 8+
- Spring Boot 2.0+

## License

MIT License © 2025-present Li Ying
