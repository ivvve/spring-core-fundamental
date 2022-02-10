package proxy.app.v1.proxy;

import com.example.trace.LogTrace;
import com.example.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import proxy.app.v1.OrderRepositoryV1;

@RequiredArgsConstructor
public class OrderRepositoryV1Proxy implements OrderRepositoryV1 {
    private final OrderRepositoryV1 orderRepository;
    private final LogTrace logTrace;

    @Override
    public void save(final String itemId) {
        final TraceStatus status = logTrace.begin("OrderRepositoryV1.save()");

        try {
            this.orderRepository.save(itemId);

            this.logTrace.end(status);
        } catch (Exception e) {
            this.logTrace.exception(status, e);
            throw e;
        }
    }
}
