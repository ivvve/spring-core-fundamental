package proxy.cglib;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;
import proxy.cglib.code.TimeMethodInterceptor;
import proxy.common.ConcreteService;

@Slf4j
public class CglibTest {
    @Test
    void cglib() {
        final ConcreteService target = new ConcreteService();

        final Enhancer enhancer = new Enhancer(); // CGLIB는 Enhancer 를 사용해서 프록시를 생성한다
        enhancer.setSuperclass(ConcreteService.class); // 어떤 구체 클래스를 상속 받을지 지정한다
        enhancer.setCallback(new TimeMethodInterceptor(target)); // 프록시에 적용할 실행 로직을 할당한다
        final ConcreteService proxy = (ConcreteService) enhancer.create(); // setSuperclass에 할당한 클래스를 상속받은 프록시를 생성한다.

        log.info("target={}", target.getClass());
        log.info("proxy={}", proxy.getClass());

        proxy.call();
    }
}
