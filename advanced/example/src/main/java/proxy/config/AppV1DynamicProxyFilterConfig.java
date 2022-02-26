package proxy.config;

import com.example.trace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import proxy.app.v1.*;

import java.lang.reflect.Proxy;

//@Configuration
public class AppV1DynamicProxyFilterConfig {
    private static final String[] PATTERNS = {
            "request*", // controller
            "orderItem*", // service
            "save*" // repository
    };

    @Bean
    public OrderControllerV1 orderControllerV1(final LogTrace logTrace) {
        final OrderControllerV1 orderController = new OrderControllerV1Impl(this.orderServiceV1(logTrace));

        return (OrderControllerV1) Proxy.newProxyInstance(
                OrderControllerV1.class.getClassLoader(),
                new Class[]{OrderControllerV1.class},
                new LogTraceFilterHandler(orderController, logTrace, PATTERNS)
        );
    }

    @Bean
    public OrderServiceV1 orderServiceV1(final LogTrace logTrace) {
        final OrderServiceV1Impl orderService = new OrderServiceV1Impl(this.orderRepositoryV1(logTrace));

        return (OrderServiceV1) Proxy.newProxyInstance(
                OrderServiceV1.class.getClassLoader(),
                new Class[]{OrderServiceV1.class},
                new LogTraceFilterHandler(orderService, logTrace, PATTERNS)
        );
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(final LogTrace logTrace) {
        final OrderRepositoryV1Impl orderRepository = new OrderRepositoryV1Impl();

        return (OrderRepositoryV1) Proxy.newProxyInstance(
                OrderRepositoryV1.class.getClassLoader(),
                new Class[]{OrderRepositoryV1.class},
                new LogTraceFilterHandler(orderRepository, logTrace, PATTERNS)
        );
    }
}
