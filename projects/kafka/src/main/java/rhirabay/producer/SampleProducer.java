package rhirabay.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class SampleProducer extends Thread{
    private final KafkaProducer producer;

    public static AtomicInteger messageNo = new AtomicInteger();

    public SampleProducer() {
        Properties properties = new Properties();
        // Kafkaの起動時のログ
        // [2019-12-08 15:00:31,587] INFO KafkaConfig values:
        //         ...
        //         port = 9092
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("client.id", "DemoProducer");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        this.producer = new KafkaProducer<>(properties);
    }

    public RecordMetadata sendSync() {
        String topic = "sample";
        String message = "hello, Kafka!";

        ProducerRecord producerRecord = new ProducerRecord(
                topic,
                messageNo.incrementAndGet(),
                message);
        try {
            return (RecordMetadata)producer.send(producerRecord).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
