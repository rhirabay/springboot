package rhirabay.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class NettyConfiguration {


    @Bean
    public WebClient nettyWebClient() {
        return WebClient.builder()
                .build();
    }
}
