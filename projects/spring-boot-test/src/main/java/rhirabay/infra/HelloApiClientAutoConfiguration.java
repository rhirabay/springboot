package rhirabay.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HelloApiClientAutoConfiguration {
    @Bean
    public HelloApiClient helloApiClient() {
        return new HelloApiClient(new RestTemplate());
    }
}
