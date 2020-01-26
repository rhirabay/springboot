package rhirabay.contract;


import io.restassured.module.webtestclient.RestAssuredWebTestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import rhirabay.ProducerController;

@ExtendWith(SpringExtension.class)
@WebFluxTest
public abstract class ContractTestBase {
    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    public void setup() {
        RestAssuredWebTestClient.webTestClient(webTestClient);
        RestAssuredWebTestClient.standaloneSetup(new ProducerController());
    }
}
