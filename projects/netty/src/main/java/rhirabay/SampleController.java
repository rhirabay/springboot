package rhirabay;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.stream.Stream;

@Controller
public class SampleController {
    @GetMapping("/sample")
    public Mono<String> sample(Model model) {
//        var attr = new ReactiveDataDriverContextVariable(Flux.just("Ryo"));
        model.addAttribute("name", "Ryo");

        model.addAttribute("items", convert(getItems()));
        return Mono.just("sample");
    }

    private ReactiveDataDriverContextVariable convert(Flux flux) {
        return new ReactiveDataDriverContextVariable(flux);

    }

    private Flux getItems() {
//        return WebClient.create()
//                .get()
//                .uri("http://localhost:8080/stream")
//                .retrieve()
//                .bodyToFlux(String.class);

        return Flux.fromStream(Stream.generate(() -> "Hello"))
                .delayElements(Duration.ofSeconds(1));
    }
}
