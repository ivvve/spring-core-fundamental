package com.example.v5;

import com.example.trace.LogTrace;
import com.example.trace.callback.TraceTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV5 {
    private final OrderServiceV5 orderService;
    private final LogTrace logTrace;

    @GetMapping("/v5/request")
    public String request(@RequestParam String itemId) {
        return new TraceTemplate(this.logTrace).execute("OrderControllerV5.request", () -> {
            orderService.orderItem(itemId);
            return "ok";
        });
    }
}
