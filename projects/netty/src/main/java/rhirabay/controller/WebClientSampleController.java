package rhirabay.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rhirabay.filter.AddHeaderFilter;
import rhirabay.filter.LoggingFilter;
import rhirabay.model.Message;

import java.time.Duration;
import java.util.stream.Stream;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WebClientSampleController {
    private final AddHeaderFilter addHeaderFilter;
    private final LoggingFilter loggingFilter;

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

    @GetMapping("/postclient")
    @ResponseBody
    public Publisher<Message> postclient() {
        return WebClient.builder()
                .filter(addHeaderFilter)
                //.filter(loggingFilter)
                .build()
                .post()
                .uri("http://localhost:8080/postserver")
                .body(Mono.just(Message.of("Ryo")), Message.class)
                .header("headerKey1", "headerValue2")
                .retrieve()
                .bodyToMono(Message.class);
    }

    @PostMapping("/postserver")
    @ResponseBody
    public Publisher<Message> postserver(
            @RequestBody Mono<Message> message
    ) {
        return message.map(v -> Message.of("Hello world, " + v.getValue()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Publisher<Message> errorHandler(Exception ex) {
        log.warn(ex.getMessage(), ex);
        return Mono.just(Message.of(ex.getMessage()));
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
