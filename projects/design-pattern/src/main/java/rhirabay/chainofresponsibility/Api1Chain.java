package rhirabay.chainofresponsibility;

import org.springframework.stereotype.Component;

@Component
public class Api1Chain extends AbstractChain {
    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public void chain() {
        try {
            System.out.println("APIコール①");
        } catch (Exception ex) {
            System.out.println("APIエラー①");
            return;
        }

        next.chain();
    }
}
