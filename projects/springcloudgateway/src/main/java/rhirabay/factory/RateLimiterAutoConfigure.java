package rhirabay.factory;

import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Component
public class RateLimiterAutoConfigure {
    @Bean
    @Profile("standalone")
    public Supplier<RateLimiter> localRateLimiterSupplier() {
        return LocalRateLimit::new;
    }

    @Bean
    @Profile("!standalone")
    public Supplier<RateLimiter> redisRateLimiterSupplier(ApplicationContext applicationContext) {
        return () -> {
            var rateLimiter = new RedisRateLimiter(1, 1);
            rateLimiter.setApplicationContext(applicationContext);
            return rateLimiter;
        };
    }

    public static class LocalRateLimit implements RateLimiter {

        com.google.common.util.concurrent.RateLimiter gauvaRateLimiter
                = com.google.common.util.concurrent.RateLimiter.create(1);

        @Override
        public Mono<Response> isAllowed(String routeId, String id) {
            return Mono.just(new Response(gauvaRateLimiter.tryAcquire(Duration.ofMillis(100)), new HashMap<>()));
        }

        @Override
        public Map getConfig() {
            return null;
        }

        @Override
        public Class getConfigClass() {
            return null;
        }

        @Override
        public Object newConfig() {
            return null;
        }
    }
}
