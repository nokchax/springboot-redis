package com.nokchax.study.nosql.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RedisApplicationTests {
    private static RedisClient client;
    private static StatefulRedisConnection<String, String> connection;
    private static RedisCommands<String, String> command;

    @BeforeAll
    public static void init() {
        client = RedisClient.create("redis://localhost:6379/0");
        connection = client.connect();
        command = connection.sync();
    }

    @AfterAll
    public static void destroy() {
        connection.close();
        client.shutdown();
    }

    @Test
    @DisplayName("레디스 서버에 연결이 잘 되고, 값이 잘 저장되는지")
    void contextLoads() {
        String key = "key";
        String value = "value";

        // before set
        assertThat(command.get(key)).isNull();

        command.set(key, value);

        // after set
        assertThat(command.get(key)).isEqualTo(value);
    }

}
