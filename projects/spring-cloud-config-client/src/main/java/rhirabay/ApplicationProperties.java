package rhirabay;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("sample")
public class ApplicationProperties {
    private String key1;
    private String key2;
}
