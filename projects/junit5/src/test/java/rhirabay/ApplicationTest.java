package rhirabay;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
@Execution(ExecutionMode.CONCURRENT)
class ApplicationTest {
    @Test
    void test() throws Exception {

        TimeUnit.SECONDS.sleep(3);
    }
}