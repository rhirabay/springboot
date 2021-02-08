package rhirabay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class SampleComponent {
    public SampleComponent() throws InterruptedException {
        TimeUnit.SECONDS.sleep(10);
        log.info("create SampleComponent");
    }
}
