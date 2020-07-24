package rhirabay;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloWorldController {
    @GetMapping("/**")
    public String helloWorld() {
        MDC.put("path", "/**");
        log.info("hello world !!!");
        return "Hello world !!!";
    }
}
