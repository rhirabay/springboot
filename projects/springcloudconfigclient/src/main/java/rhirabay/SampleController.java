package rhirabay;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SampleController {
    private final ApplicationProperties applicationProperties;

    @Value("${name}")
    private String name;

    @Value("${env}")
    private String env;

    @GetMapping("/env")
    public String env() {
        return this.env;
    }

    @GetMapping("/name")
    public String name() {
        return this.name;
    }

    @GetMapping("/prop")
    public ApplicationProperties prop() {
        return this.applicationProperties;
    }
}
