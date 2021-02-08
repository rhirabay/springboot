package rhirabay.chainofresponsibility;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chainofresponsibility")
@RequiredArgsConstructor
public class SampleController {
    private final SampleService1 sampleService1;
    private final SampleService2 sampleService2;

    // curl http://localhost:8080/chainofresponsibility/sample1
    @GetMapping("/sample1")
    public void sample1() {
        sampleService1.execute();
    }

    // curl http://localhost:8080/chainofresponsibility/sample2
    @GetMapping("/sample2")
    public void sample2() {
        sampleService2.execute();
    }
}
