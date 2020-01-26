package rhirabay;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import rhirabay.controller.ApiController;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ApiController.class)
public class ApiControllerTest {
    @Autowired
    private WebTestClient webClient;

    @Test
    public void test() {
        webClient.get()
                .uri("/api/sample")
                .exchange()
                .expectStatus().isOk();
    }
}
