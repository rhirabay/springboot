package rhirabay.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Slf4j
@Component
public class ValidateRequestBodyGatewayFilterFactory extends AbstractGatewayFilterFactory<ValidateRequestBodyGatewayFilterFactory.Config> {
    public ValidateRequestBodyGatewayFilterFactory() {
        super(Config.class);
    }

    private final List<HttpMessageReader<?>> messageReaders = HandlerStrategies.withDefaults().messageReaders();

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            log.info("config: {}", config);

            var serverRequest = ServerRequest.create(exchange, messageReaders);
            var validatedBody = serverRequest.bodyToMono(StringMap.class)
                    .map(originalBody -> {
                        var verifiedParams = new StringMap();
                        for (var key: originalBody.keySet()) {
                            var pattern = config.getPattern(key);
                            if (pattern != null) {
                                var param = originalBody.get(key);
                                if (pattern.matcher(param).find()) {
                                    verifiedParams.put(key, param);
                                }
                            }
                        }

                        return verifiedParams;
                    });

            HttpHeaders headers = new HttpHeaders();
            headers.putAll(exchange.getRequest().getHeaders());
            headers.remove(HttpHeaders.CONTENT_LENGTH);

            CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(
                    exchange, headers);

            BodyInserter bodyInserter = BodyInserters.fromPublisher(validatedBody, StringMap.class);
            return bodyInserter.insert(outputMessage, new BodyInserterContext())
                    .then(Mono.defer(() -> {
                        var decorator = new ServerHttpRequestDecorator(exchange.getRequest()) {
                            @Override
                            public Flux<DataBuffer> getBody() {
                                return outputMessage.getBody();
                            }

                            @Override
                            public HttpHeaders getHeaders() {
                                return headers;
                            }
                        };

                        return chain.filter(exchange.mutate().request(decorator).build());
                    }));
        };
    }

    @Getter
    public static class Config {
        private Map<String, Pattern> roles = new HashMap<>();

        public void setRoles(String role) {
            try {
                Map<String, String> originalRoles = new ObjectMapper().readValue(role, StringMap.class);
                for (var key: originalRoles.keySet()) {
                    this.roles.put(key, Pattern.compile(originalRoles.get(key)));
                }
            } catch (Exception ex) {
                throw new IllegalArgumentException(ex);
            }
        }

        public Pattern getPattern(String key) {
            return this.roles.getOrDefault(key, null);
        }
    }

    public static class StringMap extends HashMap<String, String> { }
}
