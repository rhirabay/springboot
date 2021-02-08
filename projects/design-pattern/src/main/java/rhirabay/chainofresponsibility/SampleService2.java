package rhirabay.chainofresponsibility;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SampleService2 {
    private final AbstractChain chainer;

    public SampleService2(List<AbstractChain> chainList) {
        this.chainer = chainList.stream()
                // orderの逆順ソート（reduceで最終的に一番小さいものを取得したいので）
                .sorted((c1, c2) -> c2.getOrder() - c1.getOrder())
                // 順々にnextを設定
                .reduce((c1, c2) -> {
                    c2.setNext(c1);
                    return c2;
                })
                .get();
    }

    public void execute() {
        chainer.chain();
    }
}
