package rhirabay;

import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import rhirabay.client.RedisClient;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@SpringBootApplication
public class Application {
    private final RedisClient redisClient;

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @GetMapping("/post")
    public Publisher<Boolean> post() {
        return redisClient.set("key", UUID.randomUUID().toString());
    }

    @GetMapping("/get")
    public Publisher<String> get() {
        return redisClient.get("key");
    }

    @GetMapping("/error")
    public Publisher<String> error() {
        return Mono.just("sample")
                .map(empty -> throwException())
                .onErrorResume(ex -> ex instanceof RuntimeException, ex -> Mono.just("runtime exception"))
                .onErrorResume(ex -> !(ex instanceof RuntimeException), ex -> Mono.just(ex.getMessage()));
    }

    private String throwException() {
        throw new RuntimeException("test");
    }
}
