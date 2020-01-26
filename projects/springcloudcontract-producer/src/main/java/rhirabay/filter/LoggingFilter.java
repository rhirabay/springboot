package rhirabay.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LoggingFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        var uri = exchange.getRequest().getURI().toString();
        var method = exchange.getRequest().getMethodValue();
        log.info("uri: {}, method: {}", uri, method);

        return chain.filter(exchange);
    }
}
