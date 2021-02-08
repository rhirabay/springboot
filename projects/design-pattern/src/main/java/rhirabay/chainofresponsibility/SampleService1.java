package rhirabay.chainofresponsibility;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SampleService1 {
    public void execute() {
        try {
            System.out.println("APIコール①");
        } catch (Exception ex) {
            System.out.println("APIエラー①");
            return;
        }

        try {
            System.out.println("DB登録①");
        } catch (Exception ex) {
            System.out.println("DBエラー①");
            return;
        }

        try {
            System.out.println("APIコール②");
        } catch (Exception ex) {
            System.out.println("APIエラー②");
            return;
        }

        try {
            System.out.println("DB登録②");
        } catch (Exception ex) {
            System.out.println("DBエラー②");
            return;
        }
    }
}
