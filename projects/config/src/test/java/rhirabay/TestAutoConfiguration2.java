package rhirabay;

import ch.qos.logback.classic.turbo.TurboFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class TestAutoConfiguration2 {
    @Bean
    public TurboFilter customTurboFilter() {
        log.info("TestAutoConfiguration2.dummyBean");
        return new CustomTurboFilter();
    }
}
