package rhirabay;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static rhirabay.Application.SERVICE_NAME;

@RestController
public class SampleController {
    private final WebClient webClient;

    public SampleController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @GetMapping("/hello")
    public Mono<String> hello() {
        return webClient.get()
                .uri("http://" + SERVICE_NAME + "/hello/anonymous")
                .retrieve()
                .bodyToMono(String.class);
    }
}
