package rhirabay.controller;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
@RequestMapping("/api")
public class ServerController {

    private AtomicInteger i = new AtomicInteger(0);

    @RequestMapping("/**")
    public Mono<RequestSummary> api(ServerHttpRequest request) {
        var summary = RequestSummary.of(
                request.getURI().toString(),
                request.getMethodValue(),
                request.getHeaders()
        );
        log.info("request summary. {}", summary);
        return Mono.just(summary);
    }

    @PostMapping("/*/post")
    public Mono<String> post(@RequestBody String body) {
        return Mono.just(body);
    }

    @RequestMapping("/*/sleep/{seconds}")
    public Mono<String> sleep(@PathVariable int seconds) throws InterruptedException {
        TimeUnit.SECONDS.sleep(seconds);
        return Mono.just("sleep " + seconds + "s");
    }

    @RequestMapping("/randomSleep/**")
    public Mono<Integer> randomSleep() {
        var sleepMillis = 1;
        var id = i.getAndAdd(1) % 2;
        if (id == 0 && i.get() < 30) {
            sleepMillis = 300;
        }

        log.info("id: {}, cnt: {}, sleep: {}", id, i.get(), sleepMillis);

        return Mono.just(sleepMillis)
                .delayElement(Duration.ofMillis(sleepMillis));
    }

    @RequestMapping("/randomStatus/**")
    public void randomStatus(ServerHttpResponse response) {
        var status = HttpStatus.OK;
        var id = i.getAndAdd(1) % 2;
        if (id == 0) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        response.setStatusCode(status);
        log.info("id: {}, cnt: {}, status: {}", id, i.get(), status);
        return;
    }

    @RequestMapping("/fallback")
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public void fallback() {
        log.warn("fallback");
        return;
    }

    @Value(staticConstructor = "of")
    public static class RequestSummary {
        private String uri;
        private String method;
        private Map<String, List<String>> headers;
    }
}
