package hello.aop.proxyvs;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
public class ProxyCastingTest {

    @Test
    void jdkProxy() {
        final MemberServiceImpl target = new MemberServiceImpl();
        final ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false); // JDK Dynamic Proxy

        final MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();
        log.info("proxy class={}", memberServiceProxy.getClass());

        assertThat(AopUtils.isJdkDynamicProxy(memberServiceProxy)).isTrue();
        assertThrows(
                ClassCastException.class,
                () -> {
                    final MemberServiceImpl memberServiceImpl = (MemberServiceImpl) memberServiceProxy;
                }
        );
    }

    @Test
    void cglibProxy() {
        final MemberServiceImpl target = new MemberServiceImpl();
        final ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true); // CGLIB

        final MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();
        log.info("proxy class={}", memberServiceProxy.getClass());

        assertThat(AopUtils.isCglibProxy(memberServiceProxy)).isTrue();
        final MemberServiceImpl memberServiceImpl = (MemberServiceImpl) memberServiceProxy;
    }

    interface MemberService {
    }

    static class MemberServiceImpl implements MemberService {
    }
}
