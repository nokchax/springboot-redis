package com.nokchax.study.nosql.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RedisApplicationTests {

    @Test
    void contextLoads() {
        String key = "key";
        String value = "value";

        RedisClient client = RedisClient.create("redis://localhost:6379/0");
        StatefulRedisConnection<String, String> connection = client.connect();
        RedisCommands<String, String> command = connection.sync();

        // before set
        assertThat(command.get(key)).isNull();

        command.set(key, value);

        // after set
        assertThat(command.get(key)).isEqualTo(value);

        connection.close();
        client.shutdown();
    }

}
