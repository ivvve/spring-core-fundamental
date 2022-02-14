package proxy.app.v1.proxy;

import com.example.trace.LogTrace;
import com.example.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import proxy.app.v1.OrderControllerV1;

@RequiredArgsConstructor
public class OrderControllerV1Proxy implements OrderControllerV1 {
    private final OrderControllerV1 orderController;
    private final LogTrace logTrace;

    @Override
    public String request(final String itemId) {
        final TraceStatus status = logTrace.begin("OrderControllerV1.orderItem()");

        try {
            final String result = this.orderController.request(itemId);

            this.logTrace.end(status);

            return result;
        } catch (Exception e) {
            this.logTrace.exception(status, e);
            throw e;
        }
    }

    @Override
    public String noLog() {
        return this.orderController.noLog();
    }
}
