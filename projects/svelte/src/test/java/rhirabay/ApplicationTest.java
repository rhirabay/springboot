package rhirabay;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Duration;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Disabled
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = RANDOM_PORT)
class ApplicationTest {
    @LocalServerPort
    private int port;

    private WebDriver webDriver;

    @Test
    void test() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/mac/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");

        WebDriver webDriver = new ChromeDriver(chromeOptions);

        webDriver.get("http://localhost:" + port + "/");
        sleep(Duration.ofSeconds(1));

        webDriver.findElement(By.tagName("div")).click();
        sleep(Duration.ofSeconds(1));

        if (webDriver != null) {
            webDriver.quit();
        }
    }

    private void sleep(Duration sleepTime) {
        try {
            Thread.sleep(sleepTime.toMillis());
        } catch (InterruptedException e) {
            // nothing to do
        }
    }
}