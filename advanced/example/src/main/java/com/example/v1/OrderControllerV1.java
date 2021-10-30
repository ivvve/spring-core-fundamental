package com.example.v1;

import com.example.trace.TraceStatus;
import com.example.trace.hello.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {
    private final OrderServiceV1 orderService;
    private final HelloTraceV1 trace;

    @GetMapping("/v1/request")
    public String request(@RequestParam String itemId) {
        final TraceStatus status = this.trace.begin("OrderControllerV1.request");

        try {
            this.orderService.orderItem(itemId);
            this.trace.end(status);

            return "ok";
        } catch (Exception e) {
            this.trace.exception(status, e);
            throw e;
        }
    }
}
