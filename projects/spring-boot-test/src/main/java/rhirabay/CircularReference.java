package rhirabay;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class CircularReference {
    @Bean
    public Sample1 sample1(Sample2 sample2) {
        return new Sample1(sample2);
    }

    @Bean
    public Sample2 sample2(Sample1 sample1) {
        return new Sample2(sample1);
    }

    @RequiredArgsConstructor
    public static class Sample1 {
        private final Sample2 sample2;
    }

    @RequiredArgsConstructor
    public static class Sample2 {
        private final Sample1 sample1;
    }
}
