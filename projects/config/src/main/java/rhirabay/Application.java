package rhirabay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
//        SpringApplication.run(Application.class);

        SpringApplication application = new SpringApplication(Application.class);
        application.setEnvironmentPrefix("prefix");
        application.run(args);
    }
}
