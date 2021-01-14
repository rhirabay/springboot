package rhirabay.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.ProxyProvider;

import java.util.UUID;

@Configuration
public class ProxyAutoConfiguration {
    @Bean
    public WebClient proxyWebClient() {
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create()
                        .proxy(typeSpec -> {
                            typeSpec.type(ProxyProvider.Proxy.HTTP)
                                    .host("localhost")
                                    .port(8080)
                                    //.username("username")
                                    //.password(s -> "password")
                                    .httpHeaders(headers -> {
                                        // ここで認証用のヘッダを乗せる
                                        headers.add("uuid", UUID.randomUUID().toString());
                                    });
                        })
                ))
                .build();
    }
}
