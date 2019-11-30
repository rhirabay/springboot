package rhirabay.client;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rhirabay.model.Product;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class ClientController {
//    private final RSocketRequester rSocketRequester;
//
//    @GetMapping("/sample1")
//    public Object sample1() {
//        return rSocketRequester.route("sample1")
//                .retrieveMono(Product.class);
//    }
}
