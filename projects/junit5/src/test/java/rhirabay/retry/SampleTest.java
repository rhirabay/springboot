package rhirabay.retry;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.RetryingTest;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class SampleTest {
    private static int cnt = 1;

//    @RetryingTest(3)
    @Test
    void test() {
        Random random = new Random();
        var bool = random.nextBoolean();
//        var bool = false;
        log.info("retry test: {}", bool);
        assertThat(bool).isTrue();
    }

    @Test
    void nothing() {
        log.info("dummy");
    }

    @Test
    void failure() {
        assertThat(true).isFalse();
    }
}
