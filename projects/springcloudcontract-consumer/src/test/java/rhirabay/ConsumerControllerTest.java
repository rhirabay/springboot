package rhirabay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;


@ExtendWith(SpringExtension.class)
@WebFluxTest
@AutoConfigureStubRunner(
        stubsMode = StubRunnerProperties.StubsMode.LOCAL,
        ids = "rhirabay:springcloudcontract-producer:+:stubs:8081"
)
public class ConsumerControllerTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    public void test_adult() {
        webClient.get()
                .uri("/checkAge?age={age}", 20)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Result.class).isEqualTo(new Result("adult"));
    }

    @Test
    public void test_child() {
        webClient.get()
                .uri("/checkAge?age={age}", 19)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Result.class).isEqualTo(new Result("child"));

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result {
        private String result;
    }
}
