package rhirabay.webclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

        WebClient webClient = WebClient.create("http://localhost:8080");

        Mono<String> sampleMono = webClient.get()
                .uri("/webclient/sample")
                .retrieve()
                .bodyToMono(String.class);

        sampleMono.subscribe(mono -> {
            log.info("subscribed");
            log.info("mono: {}", mono);
        });

        log.info("test end");
        return "finish";
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
