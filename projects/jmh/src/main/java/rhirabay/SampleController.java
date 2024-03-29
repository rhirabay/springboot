package rhirabay;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class SampleController {
    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("hello!!!");
    }
}
