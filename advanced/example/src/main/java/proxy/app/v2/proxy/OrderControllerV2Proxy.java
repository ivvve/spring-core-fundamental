package proxy.app.v2.proxy;

import com.example.trace.LogTrace;
import com.example.trace.TraceStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import proxy.app.v2.OrderControllerV2;

@RequestMapping("/v2")
@ResponseBody
public class OrderControllerV2Proxy extends OrderControllerV2 {
    private final OrderControllerV2 orderController;
    private final LogTrace logTrace;

    public OrderControllerV2Proxy(final OrderControllerV2 orderController,
                                  final LogTrace logTrace) {
        super(null);
        this.orderController = orderController;
        this.logTrace = logTrace;
    }


    @Override
    public String request(final String itemId) {
        final TraceStatus status = logTrace.begin("OrderControllerV2Proxy.orderItem()");

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
