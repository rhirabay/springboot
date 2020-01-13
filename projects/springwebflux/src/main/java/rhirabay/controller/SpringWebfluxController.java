package rhirabay.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class SpringWebfluxController {
    @GetMapping("/get")
    public Publisher<String> get() {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8081")
                .build();

        return webClient.get()
                .uri("/getServer?name={name}", "rhirabay")
                .retrieve()
                .bodyToMono(String.class);
    }

    @GetMapping("/getServer")
    public Publisher<String> getServer(@RequestParam String name) {
        return Mono.just("Hello world, " + name + "!");
    }

    @GetMapping("/post")
    public Publisher<Message> post() {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8081")
                .build();

        return webClient.post()
                .uri("/postServer")
                .body(Mono.just(Message.of("rhirabay")), Message.class)
                .retrieve()
                .bodyToMono(Message.class);
    }

    @PostMapping("/postServer")
    public Publisher<Message> postServer(@RequestBody Mono<Message> monoMessage) {
        return monoMessage.map(message -> Message.of("Hello world, " + message.getValue() + "!"));
    }

    @GetMapping("/header")
    public Publisher<Headers> header() {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8081")
                .defaultHeader("defaultKey", "defaultValue") // 固定ヘッダー
                .filter(new HeaderFilter()) // 可変ヘッダー①
                .build();

        return webClient.get()
                .uri("/headerServer")
                .header("key", "value") // 可変ヘッダー②
                .retrieve()
                .bodyToMono(Headers.class);
    }

    @GetMapping("/headerServer")
    public Publisher<Headers> headerServer(
            @RequestHeader String defaultKey,
            @RequestHeader String commonKey,
            @RequestHeader String key
    ) {
        return Mono.just(Headers.of(defaultKey, commonKey, key));
    }

    @GetMapping("/clientLogging")
    public Publisher<String> clientLogging() {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8081")
                .filter(new ClientLoggingFilter())
                .build();

        return webClient.get()
                .uri("/getServer?name={name}", "rhirabay")
                .retrieve()
                .bodyToMono(String.class);
    }
}

@Slf4j
class ClientLoggingFilter implements ExchangeFilterFunction {
    @Override
    public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {
        // request logging
        log.info("request start. uri=[{}], method=[{}]", request.url(), request.method());
        var start = System.currentTimeMillis();
        return next.exchange(request)
                .doOnSuccess(clientResponse -> {
                    var end = System.currentTimeMillis();
                    // response logging
                    log.info("request end in {}ms. uri=[{}], status=[{}]", end - start, request.url(), clientResponse.rawStatusCode());
                });
    }
}

class HeaderFilter implements ExchangeFilterFunction {
    @Override
    public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {
        ClientRequest wrappedRequest = ClientRequest.from(request)
                .header("commonKey", "commonValue")
                .build();
        return next.exchange(wrappedRequest);
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
class Headers {
    private String defaultKey;
    private String commonKey;
    private String key;
}

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
class Message {
    private String value;
}
