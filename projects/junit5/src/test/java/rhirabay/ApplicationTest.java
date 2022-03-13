package rhirabay;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.TimeUnit;

@Slf4j
//@Execution(ExecutionMode.CONCURRENT)
class ApplicationTest {

    @Execution(ExecutionMode.CONCURRENT)
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,7,8,9,10})
    void test(int num) throws Exception {
        log.info("test1. thread name: {}, num: {}", Thread.currentThread().getName(), num);
        TimeUnit.SECONDS.sleep(3);
    }
}