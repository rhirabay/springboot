package rhirabay.infra;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class HelloApiClientAdvice {

    @Before("execution(* rhirabay.infra.HelloApiClient.hello())")
    public void advice() {
        log.info("execute HelloApiClient.hello");
    }
}
