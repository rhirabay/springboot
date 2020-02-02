package rhirabay.client;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class RedisClient {
    private final ReactiveRedisTemplate<String, String> redisTemplate;

    public Mono<Boolean> set(String key, String value) {
        return redisTemplate.opsForValue().set(key, value);
    }

    public Mono<String> get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
