package rhirabay.mystream;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@EnableBinding({Sink.class, Source.class})
@RestController
@RequiredArgsConstructor
public class Demo {
//    @StreamListener(Sink.INPUT)
//    @SendTo(Source.OUTPUT)
//    public String handle(String message) {
//        return String.format("Received: %s", message);
//    }

    private final MySource source;

    @GetMapping("/mystream/**")
    public String demo() {
        source.output();

        return "demo";
    }
}
