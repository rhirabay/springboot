package rhirabay.mystream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.binder.AbstractMessageChannelBinder;
import org.springframework.cloud.stream.binder.ConsumerProperties;
import org.springframework.cloud.stream.binder.ProducerProperties;
import org.springframework.cloud.stream.provisioning.ConsumerDestination;
import org.springframework.cloud.stream.provisioning.ProducerDestination;
import org.springframework.integration.core.MessageProducer;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Slf4j
public class MyMessageChannelBinder
        extends AbstractMessageChannelBinder<ConsumerProperties, ProducerProperties, MyProvisioningProvider> {
    public MyMessageChannelBinder(String[] headersToEmbed, MyProvisioningProvider provisioningProvider) {
        super(headersToEmbed, provisioningProvider);
    }

    @Override
    protected MessageHandler createProducerMessageHandler(ProducerDestination destination,
                                                          ProducerProperties producerProperties,
                                                          MessageChannel errorChannel) throws Exception {

        return message -> log.info("handle message: {}", message.getPayload());
    }

    @Override
    protected MessageProducer createConsumerEndpoint(ConsumerDestination destination,
                                                     String group,
                                                     ConsumerProperties properties) throws Exception {
        return new MyMessageProducer();
    }
}


