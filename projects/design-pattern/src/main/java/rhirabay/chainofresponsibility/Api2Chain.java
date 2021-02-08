package rhirabay.chainofresponsibility;

import org.springframework.stereotype.Component;

@Component
public class Api2Chain extends AbstractChain {
    @Override
    public int getOrder() {
        return 3;
    }

    @Override
    public void chain() {
        try {
            System.out.println("APIコール②");
        } catch (Exception ex) {
            System.out.println("APIエラー②");
            return;
        }

        next.chain();
    }
}
