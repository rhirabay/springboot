package rhirabay;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RequiredArgsConstructor
@RestController
public class SampleController {
    private final RedisTemplate<String, String> redisTemplate;

    @GetMapping("/set")
    public void set() {
        redisTemplate.opsForValue().set("key", "value");
    }

    @GetMapping("/get")
    public String get() {
        return redisTemplate.opsForValue().get("key");
    }

    @GetMapping("/set/{key}/{value}")
    public void set(@PathVariable String key, @PathVariable String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @GetMapping("/keyCount/{key}")
    public long keyCount(@PathVariable String key) {
        return redisTemplate.countExistingKeys(Arrays.asList(key + ".*"));
    }
}
