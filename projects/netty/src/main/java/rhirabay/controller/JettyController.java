package rhirabay.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import rhirabay.filter.ApigwExchangeFilter;
import rhirabay.model.Message;

@Slf4j
@RestController
@RequestMapping("/jetty")
@RequiredArgsConstructor
public class JettyController {
    private final WebClient jettyWebClient;
    private final ApigwExchangeFilter apigwExchangeFilter;

    @GetMapping("/client")
    public Publisher<Message> client() {
        return jettyWebClient.post()
                .uri("http://localhost:8080/jetty/server")
                .body(Mono.just(Message.of("this is jetty client")), Message.class)
                .retrieve()
                .bodyToMono(Message.class);
    }

    @PostMapping("/server")
    public Publisher<Message> server(@RequestBody Mono<Message> message) {
        return message
                .map(msg -> {
                    log.info("msg: {}", msg.getValue());
                    return msg;
                })
                .map(msg -> Message.of("Hello world ! " + msg.getValue()));
    }

    @GetMapping("/test")
    @ResponseBody
    public Publisher<String> test() {
        var body = "{\n" +
                "  \"bankAccountName\" : \"TEST\",\n" +
                "  \"bankCode\" : \"0001\",\n" +
                "  \"bankBranchCode\" : \"001\",\n" +
                "  \"bankAccountType\" : \"1\",\n" +
                "  \"bankAccountNumber\" : \"1234567\"\n" +
                "}";

        return WebClient.builder()
                .filter(apigwExchangeFilter)
                .build()
                .post()
                .uri("https://dev-bankapi01.howl.vip.ssk.ynwm.yahoo.co.jp/bankSecureApi/v1/registerBankAccount")
                .body(Mono.just(body), String.class)
                .retrieve()
                .bodyToMono(String.class);
    }

    @ExceptionHandler(Exception.class)
    public Publisher<Message> exception(Exception exception) {
        log.warn(exception.getMessage(), exception);
        return Mono.just(Message.of(exception.getMessage()));
    }
}
