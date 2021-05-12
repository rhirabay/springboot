package rhirabay;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
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

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = RANDOM_PORT)
class ApplicationSelenideTest {
    @LocalServerPort
    private int port;

    @Test
    void test() {
        Configuration.browser = WebDriverRunner.CHROME;
        Configuration.headless = true;
        Configuration.baseUrl = "http://localhost:" + port;

        open("/");
        $("div").click();
    }

    private void sleep(Duration sleepTime) {
        try {
            Thread.sleep(sleepTime.toMillis());
        } catch (InterruptedException e) {
            // nothing to do
        }
    }
}