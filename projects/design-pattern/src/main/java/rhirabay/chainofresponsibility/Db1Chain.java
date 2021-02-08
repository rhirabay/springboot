package rhirabay.chainofresponsibility;

import org.springframework.stereotype.Component;

@Component
public class Db1Chain extends AbstractChain {
    @Override
    public int getOrder() {
        return 2;
    }

    @Override
    public void chain() {
        try {
            System.out.println("DB登録①");
        } catch (Exception ex) {
            System.out.println("DBエラー①");
            return;
        }

        next.chain();
    }
}
