package proxy.app.v2.proxy;

import com.example.trace.LogTrace;
import com.example.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import proxy.app.v2.OrderRepositoryV2;

@RequiredArgsConstructor
public class OrderRepositoryV2Proxy extends OrderRepositoryV2 {
    private final OrderRepositoryV2 orderRepository;
    private final LogTrace logTrace;

    @Override
    public void save(final String itemId) {
        final TraceStatus status = logTrace.begin("OrderRepositoryV2.save()");

        try {
            this.orderRepository.save(itemId);

            this.logTrace.end(status);
        } catch (Exception e) {
            this.logTrace.exception(status, e);
            throw e;
        }
    }
}
