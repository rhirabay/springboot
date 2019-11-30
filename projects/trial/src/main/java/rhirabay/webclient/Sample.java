package rhirabay.webclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@Controller
@Slf4j
public class Sample {
    @GetMapping("/webclient/test")
    @ResponseBody
    public String test() {
        log.info("test start");


        ExchangeFilterFunction filter = (ClientRequest request, ExchangeFunction next) -> {
            log.info("request url: {}", request.url());
            return next.exchange(request);
        };

        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8080")
                .defaultCookie("Y", "ycookie")
                .defaultCookie("T", "tcookie")
                .defaultHeader("header1", "value1")
                .defaultHeader("header2", "value2")
                .filter(filter)
                .build();

        webClient.get()
                .uri("/webclient/sample")
                .cookie("n_", "n_cookie")
                .header("header3", "value3")
                .header("header4", "value4")
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(mono -> {
                    log.info("subscribed");
                    log.info("mono: {}", mono);
                });

        log.info("test end");
        return "finish";
    }

    @GetMapping("/webclient/webflux")
    @ResponseBody
    public Mono<String> webflux() {
        log.info("test start");

        WebClient webClient = WebClient.create("http://localhost:8080");

        return webClient.get()
                .uri("/webclient/sample")
                .retrieve()
                .bodyToMono(String.class);

    }

    @GetMapping("/webclient/sample")
    @ResponseBody
    public String sample() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception ex) {
            // nothing to do
        }
        return "sample";
    }
}
