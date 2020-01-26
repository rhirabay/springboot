package rhirabay;

import lombok.Data;
import lombok.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ProducerController {
    // curl localhost:8081/checkAge -X POST -H 'Content-Type: application/json' -d '{"age":20}'
    @PostMapping("/checkAge")
    public Mono<Result> checkAge(@RequestBody Person person) {
        return Mono.just(person)
                .map(Person::getAge)
                .map(v -> v >= 20 ? "adult" : "child")
                .map(Result::new);
    }

    @Value
    public static class Result {
        private String result;
    }

    @Data
    public static class Person {
        private int age;
    }
}
