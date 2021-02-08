package rhirabay.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rhirabay.infra.db.UserEntity;
import rhirabay.infra.db.UserMapper;

@RestController
@RequiredArgsConstructor
public class SampleController {
    private final UserMapper userMapper;

    @GetMapping("/**")
    public String hello() {
        return "hello";
    }

    @GetMapping("/users")
    public Object users() {
        return userMapper.findAll();
    }

    @PostMapping("/registerUser")
    public void registerUser(@RequestBody UserEntity user) {
        userMapper.insert(user);
    }
}
