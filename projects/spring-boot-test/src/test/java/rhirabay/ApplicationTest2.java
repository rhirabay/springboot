package rhirabay;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTestBase
class ApplicationTest2 {
    @Test
    void test() {
        System.out.println("execute test2");
    }
}