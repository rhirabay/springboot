package rhirabay;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
