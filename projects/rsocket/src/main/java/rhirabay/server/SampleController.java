package rhirabay.server;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;
import rhirabay.model.Product;

@Controller
public class SampleController {

    @MessageMapping("sample1")
    public Mono<Product> getProducts() {
        return Mono.just(Product.of("じゃがりこ", 85));
    }
}
