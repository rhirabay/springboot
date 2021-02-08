package rhirabay;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.stereotype.Component;
import redis.embedded.RedisServer;

import javax.annotation.PreDestroy;

@Component
public class RedisAutoConfiguration {
    private static RedisServer redisServer = null;

    /**
     * constructor
     */
    public RedisAutoConfiguration(RedisProperties redisProperties) {
        // サーバーを起動
        start(redisProperties);
    }

    public static synchronized void start(RedisProperties redisProperties) {
        if (redisServer == null) {
            redisServer = new RedisServer(redisProperties.getPort());
            redisServer.start();
        }
    }

    @PreDestroy
    public void preDestroy() {
        stop();
    }

    public static synchronized void stop() {
        if (redisServer != null) {
            redisServer.stop();
            redisServer = null;
        }
    }
}
