package rhirabay.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import rhirabay.infrastructure.JunitSampleClient;

@WebFluxTest(JunitSampleController.class)
class JunitSampleControllerTest {
    @Autowired
    private WebTestClient webClient;

    @MockBean
    private JunitSampleClient junitSampleClient;

    @Test
    void test() {
        Mockito.when(junitSampleClient.ping())
                .thenReturn(Mono.just("pong"));

        webClient.get()
                .uri("/junit/sample")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("pong");
    }
}