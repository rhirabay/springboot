package rhirabay.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class HelloApiClient {
    private final RestTemplate restTemplate;

    public String hello() {
        return restTemplate.getForObject("/hello", String.class);
    }
}
