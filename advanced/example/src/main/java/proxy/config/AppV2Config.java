package proxy.config;

import com.example.trace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import proxy.app.v2.OrderControllerV2;
import proxy.app.v2.OrderRepositoryV2;
import proxy.app.v2.OrderServiceV2;
import proxy.app.v2.proxy.OrderControllerV2Proxy;
import proxy.app.v2.proxy.OrderRepositoryV2Proxy;
import proxy.app.v2.proxy.OrderServiceV2Proxy;

@Configuration
public class AppV2Config {
    @Bean
    public OrderControllerV2 orderControllerV2(final LogTrace logTrace) {
        return new OrderControllerV2Proxy(
                new OrderControllerV2(this.orderServiceV2(logTrace)),
                logTrace
        );
    }

    @Bean
    public OrderServiceV2 orderServiceV2(final LogTrace logTrace) {
        return new OrderServiceV2Proxy(
                new OrderServiceV2(this.orderRepositoryV2(logTrace)),
                logTrace
        );
    }

    @Bean
    public OrderRepositoryV2 orderRepositoryV2(final LogTrace logTrace) {
        return new OrderRepositoryV2Proxy(
                new OrderRepositoryV2(),
                logTrace
        );
    }
}
