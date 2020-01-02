package rhirabay;

import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.client.LoadBalancedRSocketMono;
import io.rsocket.client.filter.RSocketSupplier;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.metadata.WellKnownMimeType;
import io.rsocket.transport.netty.client.TcpClientTransport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.util.MimeTypeUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Collections;

@Slf4j
@Configuration
public class RsocketConfiguration {

    @Bean
    public RSocket rsocket() {
        return RSocketFactory
                .connect()
                .dataMimeType(MimeTypeUtils.APPLICATION_JSON_VALUE)
                .metadataMimeType(WellKnownMimeType.MESSAGE_RSOCKET_ROUTING.getString())
                .frameDecoder(PayloadDecoder.ZERO_COPY)
                .transport(TcpClientTransport.create(7000))
                .start()
                .doOnSubscribe(s -> log.info("RSocket connection established."))
                .block();
    }

    @Bean
    public RSocketRequester rSocketRequester(RSocket rsocket, RSocketStrategies rSocketStrategies) {
        return RSocketRequester.wrap(
                rsocket,
                MimeTypeUtils.APPLICATION_JSON,
                MimeTypeUtils.parseMimeType(WellKnownMimeType.MESSAGE_RSOCKET_ROUTING.getString()),
                rSocketStrategies);
    }

    @Bean
    public Mono<RSocketRequester> monoRSocketRequester(RSocketStrategies rSocketStrategies) {
        return LoadBalancedRSocketMono
                .create(Flux.just(Collections.singleton(new RSocketSupplier(() -> RSocketFactory
                        .connect()
                        .dataMimeType(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .metadataMimeType(WellKnownMimeType.MESSAGE_RSOCKET_ROUTING.getString())
                        .frameDecoder(PayloadDecoder.ZERO_COPY)
                        .transport(TcpClientTransport.create(7000))
                        .start()
                        .doOnSubscribe(s -> log.info("RSocket connection established."))
                ))))
                .map(rsocket -> RSocketRequester.wrap(
                        rsocket,
                        MimeTypeUtils.APPLICATION_JSON,
                        MimeTypeUtils.parseMimeType(WellKnownMimeType.MESSAGE_RSOCKET_ROUTING.getString()),
                        rSocketStrategies)
        );
    }
}
