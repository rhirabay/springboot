package rhirabay.configuration;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.JettyClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.nio.ByteBuffer;

@Slf4j
@Configuration
public class JettyConfiguration {
    @Bean
    public HttpClient jettyHttpClient() {
        return new HttpClient() {
            @Override
            public Request newRequest(URI uri) {
                Request request = super.newRequest(uri);
                request.onRequestContent((req, content) -> {
                    var body = convertString(content);
                    log.info("content: {}", content);
                    log.info("request body: {}", body);
                });
                request.onResponseContent((res, content) -> {
                    var body = convertString(content);
                    log.info("response body: {}", content);
                });
                return request;
            }
        };
    }

    @Bean
    public WebClient jettyWebClient(HttpClient jettyHttpClient) {
        return WebClient.builder()
                .clientConnector(new JettyClientHttpConnector(jettyHttpClient))
                .build();
    }

    private String convertString(ByteBuffer content) {
        var size = content.remaining();
        var byteArray = new byte[size];


        System.arraycopy(content.array(), 0, byteArray, 0, size);

        return new String(byteArray);
    }
}
