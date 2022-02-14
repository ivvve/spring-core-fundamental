package proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import proxy.jdkdynamic.code.*;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA() {
        final AInterface a = new AImpl();
        final TimeInvocationHandler aHandler = new TimeInvocationHandler(a);
        final AInterface proxy = (AInterface) Proxy.newProxyInstance(
                AInterface.class.getClassLoader(),
                new Class[]{AInterface.class},
                aHandler
        );

        proxy.call();

        log.info("targetClass={}", a.getClass());
        log.info("proxyClass={}", proxy.getClass());
    }

    @Test
    void dynamicB() {
        final BInterface b = new BImpl();
        final TimeInvocationHandler bHandler = new TimeInvocationHandler(b);
        final BInterface proxy = (BInterface) Proxy.newProxyInstance(
                BInterface.class.getClassLoader(),
                new Class[]{BInterface.class},
                bHandler
        );

        proxy.call();

        log.info("targetClass={}", b.getClass());
        log.info("proxyClass={}", proxy.getClass());
    }
}
