package rhirabay;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SampleController {
    private final UserRepository userRepository;

    @GetMapping("/user/{id}")
    public Object sample(@PathVariable String id) {
        return userRepository.findById(id);
    }

    @PostMapping("/user/{id}/{name}")
    public void sample(@PathVariable String id, @PathVariable String name) {
        userRepository.save(User.builder()
                .id(id)
                .name(name)
                .build());
    }
}
