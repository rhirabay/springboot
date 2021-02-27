package rhirabay.influxdb;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.influx.InfluxConfig;
import io.micrometer.influx.InfluxMeterRegistry;

import java.time.Duration;

public class InfluxdbSample {
    public static void main(String[] args) {
        InfluxConfig config = new InfluxConfig() {
            @Override
            public Duration step() {
                return Duration.ofSeconds(10);
            }

            @Override
            public String db() {
                return "mydb";
            }

            @Override
            public String get(String k) {
                return null; // accept the rest of the defaults
            }
        };

        MeterRegistry registry = new InfluxMeterRegistry(config, Clock.SYSTEM);
    }
}
