package rhirabay.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class JunitSampleClient {
    private final WebClient webClient;

    public JunitSampleClient(@Value("{baseUrl}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<String> ping() {
        return webClient.get()
              .uri("/ping")
              .retrieve()
              .bodyToMono(String.class);
    }
}
