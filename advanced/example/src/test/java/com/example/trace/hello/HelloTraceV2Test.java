package com.example.trace.hello;

import com.example.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class HelloTraceV2Test {
    HelloTraceV2 trace = new HelloTraceV2();

    @Test
    void begin_end_depth2() {
        final TraceStatus status1 = trace.begin("hello1");
        final TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_exception_depth2() {
        final TraceStatus status1 = trace.begin("hello1");
        final TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.exception(status2, new RuntimeException("error2"));
        trace.exception(status1, new RuntimeException("error1"));
    }
}
