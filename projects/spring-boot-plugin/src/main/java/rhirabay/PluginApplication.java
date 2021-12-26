package rhirabay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.plugin.core.Plugin;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.plugin.core.config.EnablePluginRegistries;
import org.springframework.stereotype.Component;

@Slf4j
@SpringBootApplication
@EnablePluginRegistries(WriterPlugin.class)
public class PluginApplication {
    public static void main(String[] args) {
        SpringApplication.run(PluginApplication.class);
    }

    @Bean
    ApplicationRunner runner(PluginRegistry<WriterPlugin, String> plugins) {
        return arg -> {
            plugins.getPluginsFor("csv")
                    .forEach(writerPlugin -> writerPlugin.write("Hello"));
        };
    }
}

interface WriterPlugin extends Plugin<String> {
    void write(String message);
}

@Slf4j
@Component
class CsvWriter implements WriterPlugin {

    @Override
    public void write(String message) {
        log.info("write csv: {}", message);
    }

    @Override
    public boolean supports(String delimiter) {
        return delimiter.equalsIgnoreCase("csv");
    }
}