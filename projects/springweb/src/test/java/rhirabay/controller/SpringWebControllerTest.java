package rhirabay.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@AutoConfigureMockMvc
@SpringBootTest
class SpringWebControllerTest {
    @Autowired
    WebTestClient webTestClient;

    @Test
    void test() {
        webTestClient.get()
                .uri("/hello")
                .exchange()
                .expectStatus().isOk();
    }
}