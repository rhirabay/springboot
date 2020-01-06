package rhirabay;

import org.reactivestreams.Publisher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping("/sample")
    public Publisher<String> sample() {
        return Mono.just("sample");
    }

    @RequestMapping("/google")
    private Publisher<String> google() {
        return WebClient.create()
                .get()
                .uri("https://www.google.com/")
                .retrieve()
                .bodyToMono(String.class);
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/stream")
    public Flux<String> stream() {
        return Flux.fromStream(Stream.generate(() -> "Hello"))
                .delayElements(Duration.ofSeconds(1));
    }
}