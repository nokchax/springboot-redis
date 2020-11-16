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

    @Test
    @DisplayName("레디스 서버에 연결이 잘 되고, 값이 잘 저장되는지")
    void contextLoads() {
        String key = "key";
        String value = "value";

        // beforeAll run before bean initializing
        RedisClient client = RedisClient.create("redis://localhost:6379/0");
        StatefulRedisConnection<String, String> connection = client.connect();
        RedisCommands<String, String> command = connection.sync();

        // before set
        assertThat(command.get(key)).isNull();

        command.set(key, value);

        // after set
        assertThat(command.get(key)).isEqualTo(value);
    }

}
