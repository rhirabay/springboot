package rhirabay;

import lombok.Value;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

import static rhirabay.Application.SERVICE_NAME;

@Configuration
public class HelloServerConfiguration {
    @Bean
    public ServiceInstanceListSupplier serviceInstanceListSupplier() {
        return new SampleServiceInstanceListSuppler(SERVICE_NAME);
    }

    @Value
    public static class SampleServiceInstanceListSuppler
            implements ServiceInstanceListSupplier {

        private final String serviceId;

        @Override
        public Flux<List<ServiceInstance>> get() {
            return Flux.just(Arrays.asList(
                    new DefaultServiceInstance(serviceId + "1", serviceId, "localhost", 8091, false),
                    new DefaultServiceInstance(serviceId + "2", serviceId, "localhost", 8092, false),
                    new DefaultServiceInstance(serviceId + "3", serviceId, "localhost", 8093, false)
            ));
        }
    }
}
