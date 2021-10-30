package com.example.trace.hello;

import com.example.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class HelloTraceV1Test {
    HelloTraceV1 trace = new HelloTraceV1();

    @Test
    void begin_end() {
        final TraceStatus status = trace.begin("hello");
        trace.end(status);
    }

    @Test
    void begin_exception() {
        final TraceStatus status = trace.begin("hello");
        trace.exception(status, new RuntimeException("error!"));
    }
}
