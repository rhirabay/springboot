package rhirabay.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    @GetMapping("/sample")
    public String sample() {
        return "Hello world!";
    }

    @GetMapping("/{id}")
    public String pathVariable(@PathVariable String id) {
        return id;
    }
}
