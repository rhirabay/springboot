package rhirabay;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.stereotype.Component;
import redis.embedded.RedisServer;

import javax.annotation.PreDestroy;

@Component
public class RedisAutoConfiguration {
    private final RedisServer redisServer;

    public RedisAutoConfiguration(RedisProperties redisProperties) {
        redisServer = new RedisServer(redisProperties.getPort());
        // サーバーを起動
        redisServer.start();
    }

    @PreDestroy
    public void preDestroy() {
        // サーバーを停止
        redisServer.stop();
    }
}
