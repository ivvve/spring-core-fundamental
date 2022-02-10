package proxy.app.v1.proxy;

import com.example.trace.LogTrace;
import com.example.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import proxy.app.v1.OrderServiceV1;

@RequiredArgsConstructor
public class OrderServiceV1Proxy implements OrderServiceV1 {
    private final OrderServiceV1 orderService;
    private final LogTrace logTrace;

    @Override
    public void orderItem(final String itemId) {
        final TraceStatus status = logTrace.begin("OrderServiceV1.orderItem()");

        try {
            this.orderService.orderItem(itemId);

            this.logTrace.end(status);
        } catch (Exception e) {
            this.logTrace.exception(status, e);
            throw e;
        }
    }
}
