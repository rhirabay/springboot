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
        var start = System.currentTimeMillis();
        log.info("start {}.", exchange.getRequest().getURI().getPath());
        return chain.filter(exchange)
                .doOnSuccess(v -> logging(exchange, start));
    }

    private void logging(ServerWebExchange exchange, long start) {
        // 念のため無風化
        try {
            var end = System.currentTimeMillis();
            var status = exchange.getResponse().getRawStatusCode();
            var uri = exchange.getRequest().getURI().getPath();
            log.info("  end {} [{}] in {} ms.", uri, status, end - start);
        } catch (Throwable throwable) {
            log.debug("error", throwable);
        }
    }
}
