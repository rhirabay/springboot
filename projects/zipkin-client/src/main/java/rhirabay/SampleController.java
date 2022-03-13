package rhirabay;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import rhirabay.db.User;
import rhirabay.db.UserRepository;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SampleController {
    private final RestTemplate restTemplate;
    private final UserRepository userRepository;

    @GetMapping("/proxy/hello")
    public String proxyToHello() {
        return restTemplate.getForObject("/hello", String.class);
    }

    @GetMapping("/hello")
    public String hello() {
        //userRepository.save(new User(UUID.randomUUID().toString(), "Ryo"));
        return "Hello zipkin.";
    }
}
