package rhirabay;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.stream.Stream;

@Slf4j
public class Trial {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Flux.fromStream(Stream.iterate(1, i -> ++i).limit(1_000_000))
                .parallel(100)
                .runOn(Schedulers.elastic())
                .map(item -> {
                    log.info(item.toString());
                    return item * 100;
                })
                .subscribe(Trial::toUpper);

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void toUpper(Object str) {
//        sleep(1);
        log.info(str.toString());
    }

    public static void sleep(int seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (Exception ex) {
            // nothing to do
        }
    }

    private static Flux<Integer> makeFlux() {
        return Flux.create((sub) -> {
            for (int i = 1; i <= 100; i++) {
                sub.next(i);
            }
            sub.complete();
        });
    }
}
