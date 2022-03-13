package rhirabay;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
    @GetMapping("/hello")
    public String hello(@RequestParam(required = false) String name) {
        if (StringUtils.hasLength(name)) {
            return "Hello, " + name + ".";
        } else {
            return "Hello.";
        }
    }
}
