package rhirabay;

import lombok.Data;
import lombok.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class ConsumerController {
    private WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8081")
            .build();

    // curl localhost:8080/checkAge?age=20
    @GetMapping("/checkAge")
    public Mono<Result> checkAgeLight(@RequestParam int age) {
        return webClient.post()
                .uri("/checkAge")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(new Person(age)), Person.class)
                .retrieve()
                .bodyToMono(Result.class);
    }

    @Data
    public static class Result {
        private String result;
    }

    @Value
    public static class Person {
        private int age;
    }
}
