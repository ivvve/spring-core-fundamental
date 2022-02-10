package proxy.pureproxy.concreteproxy.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ConcreteLogicTimeProxy extends ConcreteLogic {
    private final ConcreteLogic realLogic;

    @Override
    public String operation() {
        log.info("TimeDecorator 실행");
        final long startTime = System.currentTimeMillis();

        final String result = realLogic.operation();

        final long endTime = System.currentTimeMillis();
        final long resultTime = endTime - startTime;
        log.info("TimeDecorator 종료 resultTime={}", resultTime);
        return result;
    }
}
