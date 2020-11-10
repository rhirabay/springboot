package rhirabay;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = RedisTestAutoConfiguration.class)
class ApplicationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void test() {
        restTemplate.getForObject("/set", Void.class);
        var value = restTemplate.getForObject("/get", String.class);

        assertThat(value).isEqualTo("value");
    }
}