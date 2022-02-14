package proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {
    @Test
    void noReflection() {
        final Hello target = new Hello();

        log.info("start"); // 공통 로직
        final String result1 = target.callA(); // <- 호출 대상이 달라지는 부분
        log.info("result={}", result1); // 공통 로직

        log.info("start"); // 공통 로직
        final String result2 = target.callB(); // <- 호출 대상이 달라지는 부분
        log.info("result={}", result2); // 공통 로직
    }

    @Test
    void reflectionTest() throws Exception {
        final Class helloClass = Class.forName("proxy.jdkdynamic.ReflectionTest$Hello");
        final Hello hello = new Hello();

        final Method methodCallA = helloClass.getMethod("callA");
        final Object result1 = methodCallA.invoke(hello);
        log.info("result1={}", result1);

        final Method methodCallB = helloClass.getMethod("callB");
        final Object result2 = methodCallB.invoke(hello);
        log.info("result2={}", result2);
    }

    @Test
    void reflection() throws Exception {
        final Class helloClass = Class.forName("proxy.jdkdynamic.ReflectionTest$Hello");
        final Hello hello = new Hello();

        final Method methodCallA = helloClass.getMethod("callA");
        this.dynamicCall(methodCallA, hello);

        final Method methodCallB = helloClass.getMethod("callB");
        this.dynamicCall(methodCallB, hello);
    }

    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("start"); // 공통 로직
        final Object result = method.invoke(target); // <- 호출 대상이 달라지는 부분
        log.info("result={}", result); // 공통 로직
    }

    static class Hello {
        // public으로 해줘야한다
        public String callA() {
            log.info("callA");
            return "A";
        }

        // public으로 해줘야한다
        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
