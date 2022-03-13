package rhirabay.infrastructure;

import lombok.extern.slf4j.Slf4j;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class WebClientTest {
    private static MockWebServer mockServer;
    private static String baseUrl;

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
    void test() throws Exception {
        mockServer.enqueue(new MockResponse()
                .setBody("hello"));

        WebClient webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();

        String now = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssxxxxx"));

        Mono<String> response = webClient.delete()
                .uri(uriBuilder -> uriBuilder.path("/deleteApi")
                        .queryParam("requestAt", "{requestAt}")
                        .build(now))
                .retrieve()
                .bodyToMono(String.class);

        // こちらは失敗する
//        Mono<String> response = webClient.delete()
//                .uri(uriBuilder -> uriBuilder.path("/deleteApi")
//                        .queryParam("requestAt", now)
//                        .build())
//                .retrieve()
//                .bodyToMono(String.class);

        StepVerifier.create(response)
                .expectNext("hello")
                .expectComplete()
                .verify();

        RecordedRequest recordedRequest = mockServer.takeRequest();
        log.info("requested requestAt: {}", recordedRequest.getRequestUrl().queryParameter("requestAt"));
        assertThat(recordedRequest.getMethod()).isEqualToIgnoringCase("delete");
        assertThat(recordedRequest.getRequestUrl().queryParameter("requestAt")).isEqualTo(now);
    }
}
