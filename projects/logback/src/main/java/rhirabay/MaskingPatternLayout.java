package rhirabay;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.LoggerContextVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Slf4j
@Component
@EnableConfigurationProperties(MaskProperties.class)
public class MaskingPatternLayout extends PatternLayout {
    private static Pattern maskPattern = null;

    @Autowired
    private void setMaskPattern(MaskProperties maskProperties) {
        maskPattern = Pattern.compile(String.join("|", maskProperties.getMaskPatterns()));
    }

    @Override
    public String doLayout(ILoggingEvent event) {
        return masking(super.doLayout(event));
    }

    private String masking(String message) {
        if (maskPattern == null) {
            return message;
        }
        var sb = new StringBuilder(message);
        var matcher = maskPattern.matcher(message);
        if (matcher.find()) {
            for (var i = 0; i < matcher.groupCount(); i++) {
                if (matcher.group(i) == null) {
                    continue;
                }
                for (var position = matcher.start(i); position < matcher.end(i); position++) {
                    sb.setCharAt(position, '*');
                }
            }
        }

        return sb.toString();
    }
}
