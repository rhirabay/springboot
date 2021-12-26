package rhirabay.mystream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MySource {
    String OUTPUT = "mystream";

    @Output(OUTPUT)
    MessageChannel output();
}
