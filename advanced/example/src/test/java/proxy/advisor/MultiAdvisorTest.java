package proxy.advisor;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import proxy.common.ServiceImpl;
import proxy.common.ServiceInterface;

public class MultiAdvisorTest {
    @Test
    @DisplayName("여러 프록시")
    void multiAdvisorTest1() {
        // client -> proxy2(advisor2) -> proxy1(advisor1) -> target
        final ServiceImpl target = new ServiceImpl();

        /* Proxy 1 */
        final ProxyFactory proxyFactory1 = new ProxyFactory(target);
        final DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1());
        proxyFactory1.addAdvisor(advisor1);
        final ServiceInterface proxy1 = (ServiceInterface) proxyFactory1.getProxy();

        /* Proxy 2 */
        // proxy1을 사용하여 proxy2 생성
        final ProxyFactory proxyFactory2 = new ProxyFactory(proxy1);
        final DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2());
        proxyFactory2.addAdvisor(advisor2);
        final ServiceInterface proxy2 = (ServiceInterface) proxyFactory2.getProxy();

        /* 실행 */
        proxy2.save();
    }

    @Test
    @DisplayName("하나의 프록시, 여러 어드바이저")
    void multiAdvisorTest2() {
        // proxy -> advisor2 -> advisor1 -> target
        DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1());
        DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2());

        final ServiceImpl target = new ServiceImpl();
        final ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(advisor2);
        proxyFactory.addAdvisor(advisor1);
        final ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save();
    }

    @Slf4j
    static class Advice1 implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("advice1 호출");
            return invocation.proceed();
        }
    }

    @Slf4j
    static class Advice2 implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("advice2 호출");
            return invocation.proceed();
        }
    }
}
