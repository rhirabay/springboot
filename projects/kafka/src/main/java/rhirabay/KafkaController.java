package rhirabay;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import rhirabay.consumer.SampleConsumer;
import rhirabay.producer.SampleProducer;

@Controller
@RequiredArgsConstructor
@Slf4j
public class KafkaController {
    private final SampleProducer sampleProducer;
    private final SampleConsumer sampleConsumer;

    @GetMapping("/kafka/produce/sync")
    @ResponseBody
    public Object sendSync() {
        return sampleProducer.sendSync().toString();
    }

    @GetMapping("/kafka/consume/poll")
    @ResponseBody
    public Object poll() {
        return sampleConsumer.poll();
    }
}
