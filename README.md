# Redis study

## Dependency
- spring-boot-starter-data-redis
- [embedded-redis](https://github.com/ozimov/embedded-redis)

```gradle
    implementation 'org.springframework.boot:spring-boot-starter-data-redis'
    implementation 'it.ozimov:embedded-redis:0.7.3'
```

## Embedded Redis 사용법
```java
RedisServer redisServer = new RedisServer(6789);
redisServer.start();

// redis 활용

redisServer.stop();
```