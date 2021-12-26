package rhirabay.mystream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.messaging.Message;

@Slf4j
public class MyMessageProducer extends MessageProducerSupport {
    @Override
    public void doStart() {
        log.info("start MyMessageProducer");
    }

    @Override
    public void sendMessage(Message<?> message) {
        log.info("send message: {}", message.getPayload());
    }

}
