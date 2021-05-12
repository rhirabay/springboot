package rhirabay.infrastructure;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;

class JunitSampleClientTest {
    private JunitSampleClient junitSampleClient;

    public static MockWebServer mockServer;
    private static String baseUrl;

    @BeforeEach
    void beforeEach() {
        junitSampleClient = new JunitSampleClient(baseUrl);
    }

    @BeforeAll
    static void setUp() throws IOException {
        mockServer = new MockWebServer();
        mockServer.start();
        baseUrl = "http://localhost:" + mockServer.getPort();
    }

    @AfterAll
    static void tearDown() throws IOException {
        if (mockServer != null) {
            mockServer.shutdown();
        }
    }

    @Test
    void test() {
        mockServer.enqueue(new MockResponse()
                .setBody("pong"));
        Mono<String> response = junitSampleClient.ping();

        StepVerifier.create(response)
                // 要素の検証(fluxの場合には、メソッドチェーンで複数回実行)
                .expectNext("pong")
                // 要素がもうないことを検証
                .expectComplete()
                // 検証実行
                .verify();
    }
}