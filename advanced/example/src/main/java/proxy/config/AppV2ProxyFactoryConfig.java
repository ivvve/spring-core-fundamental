package proxy.config;

import com.example.trace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import proxy.app.v2.OrderControllerV2;
import proxy.app.v2.OrderRepositoryV2;
import proxy.app.v2.OrderServiceV2;
import proxy.config.advice.LogTraceAdvice;

@Slf4j
@Configuration
public class AppV2ProxyFactoryConfig {
    @Bean
    public OrderControllerV2 orderControllerV2(final LogTrace logTrace) {
        final OrderControllerV2 orderController = new OrderControllerV2(this.orderServiceV2(logTrace));

        final ProxyFactory proxyFactory = new ProxyFactory(orderController);
        proxyFactory.addAdvisor(this.getAdvisor(logTrace));

        final OrderControllerV2 proxy = (OrderControllerV2) proxyFactory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), orderController.getClass());

        return proxy;
    }

    @Bean
    public OrderServiceV2 orderServiceV2(final LogTrace logTrace) {
        final OrderServiceV2 orderService = new OrderServiceV2(this.orderRepositoryV2(logTrace));

        final ProxyFactory proxyFactory = new ProxyFactory(orderService);
        proxyFactory.addAdvisor(this.getAdvisor(logTrace));

        final OrderServiceV2 proxy = (OrderServiceV2) proxyFactory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), orderService.getClass());

        return proxy;
    }

    @Bean
    public OrderRepositoryV2 orderRepositoryV2(final LogTrace logTrace) {
        final OrderRepositoryV2 orderRepository = new OrderRepositoryV2();

        final ProxyFactory proxyFactory = new ProxyFactory(orderRepository);
        proxyFactory.addAdvisor(this.getAdvisor(logTrace));

        final OrderRepositoryV2 proxy = (OrderRepositoryV2) proxyFactory.getProxy();
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
