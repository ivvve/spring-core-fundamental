package proxy.proxyfactory;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;
import proxy.common.ConcreteService;
import proxy.common.ServiceImpl;
import proxy.common.ServiceInterface;
import proxy.common.advice.TimeAdvice;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ProxyFactoryTest {
    @Test
    @DisplayName("인터페이스가 있으면 JDK Dynamic Proxy 사용")
    void interfaceProxy() {
        final ServiceInterface target = new ServiceImpl();

        final ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        final ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        log.info("target={}", target.getClass());
        log.info("proxy={}", proxy.getClass());

        proxy.save();

        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
        assertThat(AopUtils.isCglibProxy(proxy)).isFalse();
    }

    @Test
    @DisplayName("구체 클래스만 있으면 CGLIB 사용")
    void concreteProxy() {
        final ConcreteService target = new ConcreteService();

        final ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        final ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();

        log.info("target={}", target.getClass());
        log.info("proxy={}", proxy.getClass());

        proxy.call();

        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
    }

    @Test
    @DisplayName("ProxyTargetClass 옵션을 사용하면 인터페이스가 있어도 CGLIB를 사용하고, 클래스 기반 프록시 사용")
    void proxyTargetClass() {
        final ServiceInterface target = new ServiceImpl();

        final ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true); // <===
        proxyFactory.addAdvice(new TimeAdvice());
        final ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        log.info("target={}", target.getClass());
        log.info("proxy={}", proxy.getClass());

        proxy.save();

        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
    }
}
