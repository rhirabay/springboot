package rhirabay.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rhirabay.Message;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.stream.Stream;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ServerController {

    @MessageMapping("request-response")
    public Mono<Message> requestResponse(Message message) {
        log.info("request: {}", message);
        return Mono.just(Message.of("Hello world, " + message.getValue() + " !!!"));
    }

    @MessageMapping("fire-and-forget")
    public Mono<Void> fireAndForget(Message message) {
        log.info("request: {}", message);
        return Mono.empty();
    }

    @MessageMapping("request-stream")
    public Flux<Message> requestStream(Message message) {
        log.info("[Request Stream] request: {}", message);
        return Flux.fromStream(Stream.generate(() -> Message.of(message.getValue() + "@" + ZonedDateTime.now().toEpochSecond())))
                .delayElements(Duration.ofSeconds(1));
    }
}

