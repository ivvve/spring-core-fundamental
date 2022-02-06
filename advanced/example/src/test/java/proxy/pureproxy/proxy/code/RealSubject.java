package proxy.pureproxy.proxy.code;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealSubject implements Subject {
    @SneakyThrows
    @Override
    public String operation(String input) {
        log.info("RealSubject called");
        Thread.sleep(1000); // 부하가 큰 작업
        return "output: " + input;
    }
}
