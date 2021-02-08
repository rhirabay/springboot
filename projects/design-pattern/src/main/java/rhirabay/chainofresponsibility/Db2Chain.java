package rhirabay.chainofresponsibility;

import org.springframework.stereotype.Component;

@Component
public class Db2Chain extends AbstractChain {
    @Override
    public int getOrder() {
        return 4;
    }

    @Override
    public void chain() {
        try {
            System.out.println("DB登録②");
        } catch (Exception ex) {
            System.out.println("DBエラー②");
            return;
        }
    }
}
