package rhirabay;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SampleController {
    private final SampleService sampleService;

    @GetMapping("/**")
    public String hello() {
        log.info("start API");
        try {
            sampleService.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        log.info("  end API");
        return "hello";
    }
}
