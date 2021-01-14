package rhirabay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class CopyAutoConfiguration {
    public CopyAutoConfiguration(CopyProperties copyProperties) {
        log.info("properties: {}", copyProperties);
    }
}
