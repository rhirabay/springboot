package rhirabay;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.migrator.PropertiesMigrationListener;
import org.springframework.boot.context.properties.migrator.PropertiesMigrationListenerWrapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class, TestAutoConfiguration2.class})
class PropertyMigrateTest2 {

//    @Autowired
//    private PropertiesMigrationListener migrationListener;

//    @Mock
//    private static Appender<ILoggingEvent> mockAppender;
//
//    @Captor
//    private static ArgumentCaptor<LoggingEvent> captorLoggingEvent;

    @BeforeAll
    static void init() {
        log.info("BeforeAll");
//        final Logger logger = (Logger) LoggerFactory.getLogger("org.springframework.boot.context.properties.migrator");
//        logger.addAppender(mockAppender);
    }

    @Test
    void test() {
        log.info("ok");
//        verify(mockAppender, atLeastOnce()).doAppend(captorLoggingEvent.capture());
//        log.info("all event: {}", captorLoggingEvent.getAllValues());
    }
}