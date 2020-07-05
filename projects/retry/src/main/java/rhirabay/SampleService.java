package rhirabay;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ConnectTimeoutException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SampleService {
    @Retryable(value = ConnectTimeoutException.class, stateful = false)
    public void execute() {
        log.info("exception called");
        throw new RuntimeException(new ConnectTimeoutException());
    }
}
