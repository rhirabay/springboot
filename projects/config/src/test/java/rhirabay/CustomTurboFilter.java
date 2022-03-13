package rhirabay;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.boot.context.properties.migrator.PropertiesMigrationListener;

import javax.annotation.PostConstruct;

public class CustomTurboFilter extends TurboFilter {
    @Override
    public FilterReply decide(Marker marker, Logger logger, Level level, String format, Object[] params, Throwable t) {
        if (logger.getName().equals(PropertiesMigrationListener.class.getName())) {
            if (level == Level.WARN || level == Level.ERROR) {
                throw new PropertiesMigrationException("Deprecated property exist." + format);
            }
        }

        return FilterReply.NEUTRAL;
    }

    @PostConstruct
    public void init() {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.addTurboFilter(this);
    }
}
