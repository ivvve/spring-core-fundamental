package com.example.v2;

import com.example.trace.TraceStatus;
import com.example.trace.hello.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {
    private final OrderServiceV2 orderService;
    private final HelloTraceV2 trace;

    @GetMapping("/v2/request")
    public String request(@RequestParam String itemId) {
        final TraceStatus status = this.trace.begin("OrderControllerV2.request");

        try {
            this.orderService.orderItem(status.getTraceId(), itemId);
            this.trace.end(status);

            return "ok";
        } catch (Exception e) {
            this.trace.exception(status, e);
            throw e;
        }
    }
}
