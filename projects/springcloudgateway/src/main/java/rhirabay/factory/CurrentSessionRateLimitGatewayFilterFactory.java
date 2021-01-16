package rhirabay.factory;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.setResponseStatus;

@Slf4j
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
            // セッションの一覧を取得
            String key1 = config.getKeyPrefix() + "." + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmm"));
            String key2 = config.getKeyPrefix() + "." + LocalDateTime.now().minusMinutes(1).format(DateTimeFormatter.ofPattern("yyyyMMddhhmm"));
            var count1 = redisTemplate.opsForValue().get(key1);
            var count2 = redisTemplate.opsForValue().get(key2);

            return Flux.merge(count1, count2)
                    .map(Integer::parseInt)
                    .reduce(0, (cnt1, cnt2) -> cnt1 + cnt2)
                    .flatMap(count -> {
                        // キーの個数がlimit未満であればバックポスト
                        log.info("count: {}", count);
                        if (count < config.getLimit()) {
                            return redisTemplate.opsForValue().increment(key1)
                                    //.then(Mono.defer(() -> redisTemplate.expire(key1, Duration.ofMinutes(2))))
                                    .flatMap(bool -> chain.filter(exchange))
                                    .then(Mono.defer(() -> redisTemplate.opsForValue().decrement(key1)));
                        }
                        // それ以外は429を返却
                        setResponseStatus(exchange, HttpStatus.TOO_MANY_REQUESTS);
                        return exchange.getResponse().setComplete();
                    })
                    .then();
        };
    }

    @Data
    public static class Config {
        private int limit;
        private String keyPrefix;
        private int lifetimeSeconds = 60;
    }

    public static void main(String[] args) {
        var test = Flux.merge(Flux.just(1), Flux.just(1))
                .count()
                .block();
        System.out.println(test);
    }
}
