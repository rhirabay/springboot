package rhirabay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static final String SERVICE_NAME = "hello-service";

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
