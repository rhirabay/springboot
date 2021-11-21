package rhirabay.keepalive;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/keep-alive")
public class KeepAliveController {
    private RestTemplate restTemplate;

    public KeepAliveController() {
        CloseableHttpClient httpClient = HttpClients
                .custom()
                .setConnectionTimeToLive(4, TimeUnit.SECONDS)
                .evictIdleConnections(3, TimeUnit.SECONDS)
                .build();
        this.restTemplate = new RestTemplateBuilder()
                .requestFactory(() -> new HttpComponentsClientHttpRequestFactory(httpClient))
                .build();
    }

    @GetMapping("/")
    public ResponseEntity<String> test() {
        return restTemplate.getForEntity("http://localhost:80/status.html", String.class);
    }
}
