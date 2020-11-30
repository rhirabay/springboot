package rhirabay.factory;

import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.setResponseStatus;

@Component
public class CurrentSessionRateLimitGatewayFilterFactory extends AbstractGatewayFilterFactory<CurrentSessionRateLimitGatewayFilterFactory.Config> {
    private final ReactiveRedisTemplate<String, String> redisTemplate;

    public CurrentSessionRateLimitGatewayFilterFactory(
            ReactiveRedisTemplate<String, String> redisTemplate
    ) {
        super(Config.class);
        this.redisTemplate = redisTemplate;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
                var key = config.getKeyPrefix() + "." + UUID.randomUUID().toString();
                // セッションの一覧を取得
                return redisTemplate.keys(config.getKeyPrefix() + ".*")
                        // Flux → Mono変換
                        .collectList()
                        .flatMap(list -> {
                            // キーの個数がlimit未満であればバックポスト
                            if (list.size() < config.getLimit()) {
                                return redisTemplate.opsForValue().set(key, "", Duration.ofSeconds(config.getLifetimeSeconds()))
                                        .flatMap(bool -> chain.filter(exchange));
                            }
                            // それ以外は429を返却
                            setResponseStatus(exchange, HttpStatus.TOO_MANY_REQUESTS);
                            return exchange.getResponse().setComplete();
                        })
                        .then(Mono.defer(() -> redisTemplate.delete(key)))
                        .then();
        };
    }

    @Data
    public static class Config {
        private int limit;
        private String keyPrefix;
        private int lifetimeSeconds = 60;
    }
}
