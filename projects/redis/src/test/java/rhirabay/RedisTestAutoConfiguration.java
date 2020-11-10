package rhirabay;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import redis.embedded.RedisServer;

import javax.annotation.PreDestroy;

@TestConfiguration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisTestAutoConfiguration {
    private final RedisServer redisServer;

    /**
     * constructor
     */
    public RedisTestAutoConfiguration(RedisProperties redisProperties) {
        redisServer = new RedisServer(redisProperties.getPort());
        // サーバーを起動
        redisServer.start();
    }

    @PreDestroy
    public void preDestroy() {
        redisServer.stop();
    }
}
