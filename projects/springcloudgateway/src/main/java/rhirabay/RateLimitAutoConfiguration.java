package rhirabay;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class RateLimitAutoConfiguration {
    @Bean
    public KeyResolver simpleKeyResolver() {
        return exchange -> Mono.just("sample");
    }

//    @Bean
//    public KeyResolver pathKeyResolver() {
//        return exchange -> Mono.just(exchange.getRequest().getPath().toString());
//    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeBuilder, ApplicationContext applicationContext){
        var rateLimiter = new RedisRateLimiter(1, 1);
        rateLimiter.setApplicationContext(applicationContext);

        return routeBuilder.routes()
                .route("test1", r -> r.path("/custom-ratelimit")
                        .filters(f -> f
                                .prefixPath("/api")
                                .requestRateLimiter().configure(c -> c.setRateLimiter(rateLimiter)))
                        .uri("http://localhost:8080"))
                .build();
    }
}
