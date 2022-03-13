package rhirabay;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Application.class)
@CucumberContextConfiguration
@AutoConfigureMockMvc
@CucumberOptions(publish = true,
        features = "src/test/resources",
        plugin = "pretty",
        glue = "rhirabay")
public class ApplicationTest {
//    @Test
//    void test() {
//        // nothing to do
//    }
}