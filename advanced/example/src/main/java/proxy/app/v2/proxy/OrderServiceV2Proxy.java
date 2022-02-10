package proxy.app.v2.proxy;

import com.example.trace.LogTrace;
import com.example.trace.TraceStatus;
import proxy.app.v2.OrderServiceV2;

public class OrderServiceV2Proxy extends OrderServiceV2 {
    private final OrderServiceV2 orderService;
    private final LogTrace logTrace;

    public OrderServiceV2Proxy(final OrderServiceV2 orderService, final LogTrace logTrace) {
        super(null);
        this.orderService = orderService;
        this.logTrace = logTrace;
    }

    @Override
    public void orderItem(final String itemId) {
        final TraceStatus status = logTrace.begin("OrderServiceV2.orderItem()");

        try {
            this.orderService.orderItem(itemId);

            this.logTrace.end(status);
        } catch (Exception e) {
            this.logTrace.exception(status, e);
            throw e;
        }
    }
}
