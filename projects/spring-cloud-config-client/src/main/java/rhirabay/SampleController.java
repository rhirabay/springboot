package rhirabay;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SampleController {
    private final ApplicationProperties applicationProperties;

    @GetMapping("/prop")
    public ApplicationProperties prop() {
        return this.applicationProperties;
    }
}
