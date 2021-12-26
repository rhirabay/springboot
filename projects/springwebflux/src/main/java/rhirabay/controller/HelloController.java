package rhirabay.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        log.info("hello!");
        return String.format("Hello, %s !", name);
    }

    @GetMapping("/hello/anonymous")
    public String helloWithoutName() {
        log.info("hello anonymous");
        return "Hello !";
    }
}
