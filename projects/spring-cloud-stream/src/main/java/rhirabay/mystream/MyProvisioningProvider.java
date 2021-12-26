package rhirabay.mystream;

import lombok.Builder;
import org.springframework.cloud.stream.binder.ConsumerProperties;
import org.springframework.cloud.stream.binder.ProducerProperties;
import org.springframework.cloud.stream.provisioning.ConsumerDestination;
import org.springframework.cloud.stream.provisioning.ProducerDestination;
import org.springframework.cloud.stream.provisioning.ProvisioningException;
import org.springframework.cloud.stream.provisioning.ProvisioningProvider;

public class MyProvisioningProvider implements ProvisioningProvider<ConsumerProperties, ProducerProperties> {
    @Override
    public ProducerDestination provisionProducerDestination(String name, ProducerProperties properties) throws ProvisioningException {
        return MessageDestination.builder()
                .name(name)
                .build();
    }

    @Override
    public ConsumerDestination provisionConsumerDestination(String name, String group, ConsumerProperties properties) throws ProvisioningException {
        return MessageDestination.builder()
                .name(name)
                .build();
    }

    @Builder
    private static class MessageDestination implements ProducerDestination, ConsumerDestination {
        private String name;

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public String getNameForPartition(int partition) {
            throw new UnsupportedOperationException("Partitioning is not implemented for file messaging.");
            // return this.name;
        }
    }
}
