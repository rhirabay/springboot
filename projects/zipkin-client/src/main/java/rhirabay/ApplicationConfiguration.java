package rhirabay;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.sleuth.zipkin2.ZipkinRestTemplateCustomizer;
import org.springframework.cloud.sleuth.zipkin2.ZipkinRestTemplateProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public RestTemplate myRestTemplate() {
        return new RestTemplateBuilder()
                .rootUri("http://localhost:8080")
                .build();
    }
}
