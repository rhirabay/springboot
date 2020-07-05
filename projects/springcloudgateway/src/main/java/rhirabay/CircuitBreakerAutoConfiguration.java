package rhirabay;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Configuration
public class CircuitBreakerAutoConfiguration {

    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(CircuitBreakerConfig.custom()
                        .failureRateThreshold(30) // エラー比率
                        .slidingWindowSize(10) // エラー継続件数
                        .waitDurationInOpenState(Duration.ofSeconds(30)) //  OPEN→HALF OPENまでの間隔
                        .permittedNumberOfCallsInHalfOpenState(5) // HALF OPEN時のリクエスト数
                        // レスポンスタイムが200ms以上のリクエストが50%を超えるとOPEN状態に遷移
                        // それまでfallbackには流さない
                        .slowCallDurationThreshold(Duration.ofMillis(200))
                        .slowCallRateThreshold(70)
                        .build())
                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(100)).build())
                .build());
    }


    // 10秒間、30%の割合でエラーが続いたらOPEN
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> customizer1() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(CircuitBreakerConfig.custom()
                        .failureRateThreshold(30) // エラー比率
                        .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.TIME_BASED)
                        .slidingWindowSize(10) // エラー継続秒数
                        .build())
                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofMillis(200)).build())
                .build());
    }

    // HALF OPENの間隔と件数の制御
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> customizer2() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(CircuitBreakerConfig.custom()
                        .failureRateThreshold(30) // エラー比率
                        .slidingWindowSize(10) // エラー継続件数
                        .waitDurationInOpenState(Duration.ofSeconds(30)) //  OPEN→HALF OPENまでの間隔
                        .permittedNumberOfCallsInHalfOpenState(5) // HALF OPEN時のリクエスト数
                        .build())
                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofMillis(200)).build())
                .build());
    }
}
