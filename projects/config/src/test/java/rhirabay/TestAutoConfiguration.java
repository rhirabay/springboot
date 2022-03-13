package rhirabay;

import org.springframework.boot.context.properties.migrator.PropertiesMigrationListenerWrapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class TestAutoConfiguration {
    @Bean
    public PropertiesMigrationListenerWrapper propertiesMigrationListenerWrapper() {
        return new PropertiesMigrationListenerWrapper();
    }
}
