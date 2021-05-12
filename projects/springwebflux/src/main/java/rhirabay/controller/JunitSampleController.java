package rhirabay.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import rhirabay.infrastructure.JunitSampleClient;

@RestController
@RequiredArgsConstructor
public class JunitSampleController {
    private final JunitSampleClient junitSampleClient;

    @GetMapping("/junit/sample")
    public Mono<String> sample() {
        return junitSampleClient.ping();
    }
}
