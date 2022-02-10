package proxy.config;

import com.example.trace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import proxy.app.v1.*;
import proxy.app.v1.proxy.OrderControllerV1Proxy;
import proxy.app.v1.proxy.OrderRepositoryV1Proxy;
import proxy.app.v1.proxy.OrderServiceV1Proxy;

@Configuration
public class AppV1Config {
    @Bean
    public OrderControllerV1 orderControllerV1(final LogTrace logTrace) {
//        return new OrderControllerV1Impl(this.orderServiceV1());
        return new OrderControllerV1Proxy(
                new OrderControllerV1Impl(this.orderServiceV1(logTrace)),
                logTrace
        );
    }

    @Bean
    public OrderServiceV1 orderServiceV1(final LogTrace logTrace) {
//        return new OrderServiceV1Impl(this.orderRepositoryV1());
        return new OrderServiceV1Proxy(
                new OrderServiceV1Impl(this.orderRepositoryV1(logTrace)),
                logTrace
        );
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(final LogTrace logTrace) {
//        return new OrderRepositoryV1Impl();
        return new OrderRepositoryV1Proxy(
                new OrderRepositoryV1Impl(),
                logTrace
        );
    }
}
