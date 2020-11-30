package rhirabay.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RequestMapping("/redis")
@RequiredArgsConstructor
@RestController
public class RedisController {
    private final ReactiveRedisTemplate<String, String> redisTemplate;

    @GetMapping("/get/{key}")
    public Mono<String> get(@PathVariable String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @GetMapping("/set/{key}/{value}")
    public Mono<Boolean> get(@PathVariable String key, @PathVariable String value) {
        return redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(60));
    }

    @GetMapping("/del/{key}")
    public Mono<Long> del(@PathVariable String key) {
        return redisTemplate.delete(key);
    }
}
