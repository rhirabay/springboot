package rhirabay.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
public class SpringWebController {
    @GetMapping("/get")
    public String get() {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri("http://localhost:8082")
                .build();

        return restTemplate.getForObject("/getServer?name={name}", String.class, "rhirabay");
    }

    @GetMapping("/getServer")
    public String getServer(@RequestParam String name) {
        return "Hello world, " + name + "!";
    }

    @GetMapping("/post")
    public Message post() {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri("http://localhost:8082")
                .build();

        return restTemplate.postForObject("/postServer", Message.of("rhirabay"), Message.class);
    }

    @PostMapping("/postServer")
    public Message postServer(@RequestBody Message message) {
        return Message.of("Hello world, " + message.getValue() + "!");
    }

    @GetMapping("/header")
    public Headers header() {

        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri("http://localhost:8082")
                .defaultHeader("defaultKey", "defaultValue") // 固定ヘッダー
                .additionalInterceptors(new HeaderInterceptor()) // 可変ヘッダー①
                .build();

        HttpHeaders header = new HttpHeaders(); // 可変ヘッダー②
        header.add("key", "value");
        return restTemplate.exchange("/headerServer", HttpMethod.GET, new HttpEntity<>(header), Headers.class)
                .getBody();
    }

    @GetMapping("/headerServer")
    public Headers headerServer(
            @RequestHeader String defaultKey,
            @RequestHeader String commonKey,
            @RequestHeader String key
    ) {
        return Headers.of(defaultKey, commonKey, key);
    }

    @GetMapping("/clientLogging")
    public String clientLogging() {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri("http://localhost:8082")
                .additionalInterceptors(new ClientLoggingInterceptor())
                .build();

        return restTemplate.getForObject("/getServer?name={name}", String.class, "rhirabay");
    }
}

@Slf4j
class ClientLoggingInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        // request logging
        log.info("request start. uri=[{}], method=[{}]", request.getURI(), request.getMethod());
        var start = System.currentTimeMillis();
        var response = execution.execute(request, body);
        var end = System.currentTimeMillis();
        // response logging
        log.info("request end in {}ms. uri=[{}], status=[{}]", end - start, request.getURI(), response.getRawStatusCode());
        return response;
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
class Headers {
    private String defaultKey;
    private String commonKey;
    private String key;
}

class HeaderInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add("commonKey", "commonValue");
        return execution.execute(request, body);
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
class Message {
    private String value;
}
