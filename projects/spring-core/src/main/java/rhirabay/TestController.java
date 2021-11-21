package rhirabay;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rhirabay.property.JsonFileProperties;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final JsonFileProperties jsonFileProperties;

    @GetMapping("/json/property")
    public JsonFileProperties jsonFileProperties() {
        return jsonFileProperties;
    }
}
