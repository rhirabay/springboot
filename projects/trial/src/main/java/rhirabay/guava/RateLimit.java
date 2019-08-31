package rhirabay.guava;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import wiremock.com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

@RestController
public class RateLimit {
    private RateLimiter rateLimiter = RateLimiter.create(2);

    @GetMapping("/guava/ratelimit")
    public String guava_rateLimit() {
        if (rateLimiter.tryAcquire(1,100, TimeUnit.MICROSECONDS)) {
            return "ok";
        } else {
            return "over rate limit";
        }
    }

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(10);
        rateLimiter.acquire(1);
    }
}
