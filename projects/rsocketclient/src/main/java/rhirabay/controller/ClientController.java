package rhirabay.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rhirabay.model.Message;


@Slf4j
@RestController
@RequiredArgsConstructor
public class ClientController {
    private final RSocketRequester rSocketRequester;
    private final Mono<RSocketRequester> monoRSocketRequester;

    @GetMapping("/request-response")
    public Mono<Message> requestResponse() {
        return monoRSocketRequester.flatMap(rSocketRequester ->
                rSocketRequester
                        .route("request-response")
                        .data(Message.of("Ryo"))
                        .retrieveMono(Message.class));
    }

    @MessageExceptionHandler
    public Mono<Message> handleException(Exception e) {
        return Mono.just(Message.of(e.getMessage()));
    }

    @GetMapping("/fire-and-forget")
    public Publisher<Void> fireAndForget() {
        return monoRSocketRequester.flatMap(rSocketRequester ->
                rSocketRequester.route("fire-and-forget")
                        .data(Message.of("Ryo"))
                        .send());
    }

    @GetMapping(value = "/request-stream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Message> requestStream() {
        return monoRSocketRequester.flatMapMany(rSocketRequester ->
                rSocketRequester.route("request-stream")
                        .data(Message.of("Ryo"))
                        .retrieveFlux(Message.class));
    }
}

