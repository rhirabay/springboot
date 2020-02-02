package rhirabay.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class SampleTask {
    private AtomicInteger cnt = new AtomicInteger(0);

    @Scheduled(cron="* * * * * *")
    public void hello() {
        if (cnt.addAndGet(1) % 2 == 0) {
            log.info("(skipped)");
            return;
        }
        log.info("Hello world!!!");
    }
}
