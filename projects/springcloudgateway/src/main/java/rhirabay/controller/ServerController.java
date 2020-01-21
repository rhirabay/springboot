package rhirabay.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class ServerController {

    @GetMapping("/sample")
    public String sample() {
        return "Hello world!";
    }

    @GetMapping("/{id}")
    public String pathVariable(@PathVariable String id) {
        log.info("request id: {}", id);
        return id;
    }
}
