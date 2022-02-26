package proxy.advisor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import proxy.common.ServiceImpl;
import proxy.common.ServiceInterface;
import proxy.common.advice.TimeAdvice;

import java.lang.reflect.Method;

@Slf4j
public class AdvisorTest {
    @Test
    void advisorTest1() {
        final ServiceInterface target = new ServiceImpl();
        final ProxyFactory proxyFactory = new ProxyFactory(target);

        final DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(Pointcut.TRUE, new TimeAdvice());
        proxyFactory.addAdvisor(advisor);

        final ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        System.out.println("-----");
        proxy.save();
        System.out.println("-----");
        proxy.find();
        System.out.println("-----");
    }

    @Test
    @DisplayName("직접 만든 포인트컷")
    void advisorTest2() {
        final ServiceInterface target = new ServiceImpl();
        final ProxyFactory proxyFactory = new ProxyFactory(target);

        final DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new MyPointcut(), new TimeAdvice());
        proxyFactory.addAdvisor(advisor);

        final ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        System.out.println("-----");
        proxy.save();
        System.out.println("-----");
        proxy.find();
        System.out.println("-----");
    }

    @Test
    @DisplayName("스프링이 제공하는 포인트컷")
    void advisorTest3() {
        final ServiceInterface target = new ServiceImpl();
        final ProxyFactory proxyFactory = new ProxyFactory(target);

        final NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("save");

        final DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new TimeAdvice());
        proxyFactory.addAdvisor(advisor);

        final ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        System.out.println("-----");
        proxy.save();
        System.out.println("-----");
        proxy.find();
        System.out.println("-----");
    }

    static class MyPointcut implements Pointcut {

        @Override
        public ClassFilter getClassFilter() {
            return ClassFilter.TRUE;
        }

        @Override
        public MethodMatcher getMethodMatcher() {
            return new MyMethodMatcher();
        }
    }

    static class MyMethodMatcher implements MethodMatcher {
        private String matchName = "save";

        @Override
        public boolean matches(final Method method, final Class<?> targetClass) {
            final boolean result = method.getName().equals(this.matchName);
            log.info("포인트컷 호출 method={} targetClass={}", method.getName(), targetClass);
            log.info("포인트컷 결과 result={}", result);
            return result;

        }

        /**
         * false 인 경우 클래스의 정적 정보만 사용하기 때문에 스프링이 내부에서 캐싱을 통해 성능 향상이 가능하지만,
         * true 인 경우 매개변수가 동적으로 변경된다고 가정하기 때문에 캐싱을 하지 않는다.
         */
        @Override
        public boolean isRuntime() {
            return false;
        }

        /**
         * 이 메서드에 method , targetClass 정보가 넘어온다.
         * 이 정보로 어드바이스를 적용하지 않을지 판단할 수 있다.
         * isRuntime() 메서드가 true 인 경우 이 matches 메서드를 사용한다
         */
        @Override
        public boolean matches(final Method method, final Class<?> targetClass, final Object... args) {
            throw new UnsupportedOperationException();
        }
    }
}
