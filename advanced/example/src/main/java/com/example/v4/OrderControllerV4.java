package com.example.v4;

import com.example.trace.LogTrace;
import com.example.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {
    private final OrderServiceV4 orderService;
    private final LogTrace logTrace;

    @GetMapping("/v4/request")
    public String request(@RequestParam String itemId) {
        final AbstractTemplate<String> template = new AbstractTemplate<>(this.logTrace) {
            @Override
            protected String call() {
                orderService.orderItem(itemId);
                return "ok";
            }
        };
        return template.execute("OrderControllerV4.request()");
    }
}
