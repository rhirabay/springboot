package rhirabay;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "app")
public class MaskProperties {
    private List<String> maskPatterns;
}
