package org.springframework.boot.context.properties.migrator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationmetadata.ConfigurationMetadataRepository;
import org.springframework.boot.configurationmetadata.ConfigurationMetadataRepositoryJsonBuilder;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class PropertiesMigrationListenerWrapper
        implements ApplicationListener<SpringApplicationEvent> {

    private PropertiesMigrationReport report;

    @Override
    public void onApplicationEvent(SpringApplicationEvent event) {
        log.info("event class: {}", event.getClass());
        if (event instanceof ApplicationStartedEvent) {
            onApplicationPreparedEvent((ApplicationStartedEvent) event);
        }
    }

    @EventListener
    public void onApplicationPreparedEvent(ApplicationStartedEvent event) {
        ConfigurationMetadataRepository repository = loadRepository();
        PropertiesMigrationReporter reporter = new PropertiesMigrationReporter(repository,
                event.getApplicationContext().getEnvironment());
        this.report = reporter.getReport();
    }

    private ConfigurationMetadataRepository loadRepository() {
        try {
            return loadRepository(ConfigurationMetadataRepositoryJsonBuilder.create());
        }
        catch (IOException ex) {
            throw new IllegalStateException("Failed to load metadata", ex);
        }
    }

    private ConfigurationMetadataRepository loadRepository(ConfigurationMetadataRepositoryJsonBuilder builder)
            throws IOException {
        Resource[] resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath*:/META-INF/spring-configuration-metadata.json");
        for (Resource resource : resources) {
            try (InputStream inputStream = resource.getInputStream()) {
                builder.withJsonResource(inputStream);
            }
        }
        return builder.build();
    }

    public boolean existsDeprecatedProperty() {
        var result = false;
        String warningReport = this.report.getWarningReport();
        if (warningReport != null) {
            log.warn(warningReport);
            result = true;
        }

        String errorReport = this.report.getErrorReport();
        if (errorReport != null) {
            log.error(errorReport);
            result = false;
        }

        return result;
    }
}
