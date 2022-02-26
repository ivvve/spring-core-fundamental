package proxy.config;

import com.example.trace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import proxy.app.v1.*;
import proxy.config.advice.LogTraceAdvice;

@Slf4j
@Configuration
public class AppV1ProxyFactoryConfig {
    @Bean
    public OrderControllerV1 orderControllerV1(final LogTrace logTrace) {
        final OrderControllerV1 orderController = new OrderControllerV1Impl(this.orderServiceV1(logTrace));

        final ProxyFactory proxyFactory = new ProxyFactory(orderController);
        proxyFactory.addAdvisor(this.getAdvisor(logTrace));

        final OrderControllerV1 proxy = (OrderControllerV1) proxyFactory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), orderController.getClass());

        return proxy;
    }

    @Bean
    public OrderServiceV1 orderServiceV1(final LogTrace logTrace) {
        final OrderServiceV1 orderService = new OrderServiceV1Impl(this.orderRepositoryV1(logTrace));

        final ProxyFactory proxyFactory = new ProxyFactory(orderService);
        proxyFactory.addAdvisor(this.getAdvisor(logTrace));

        final OrderServiceV1 proxy = (OrderServiceV1) proxyFactory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), orderService.getClass());

        return proxy;
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(final LogTrace logTrace) {
        final OrderRepositoryV1 orderRepository = new OrderRepositoryV1Impl();

        final ProxyFactory proxyFactory = new ProxyFactory(orderRepository);
        proxyFactory.addAdvisor(this.getAdvisor(logTrace));

        final OrderRepositoryV1 proxy = (OrderRepositoryV1) proxyFactory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), orderRepository.getClass());

        return proxy;
    }

    private Advisor getAdvisor(final LogTrace logTrace) {
        final NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");

        final LogTraceAdvice advice = new LogTraceAdvice(logTrace);

        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
