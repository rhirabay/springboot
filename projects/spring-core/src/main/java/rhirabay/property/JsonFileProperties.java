package rhirabay.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = "classpath:json-file-property.json")
@ConfigurationProperties
public class JsonFileProperties {
    private String key1;
    private String key2;
}
