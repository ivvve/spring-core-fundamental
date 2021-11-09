package com.example.trace;

import org.junit.jupiter.api.Test;

class FieldLogTraceTest {
    FieldLogTrace trace = new FieldLogTrace();

    @Test
    void begin_end_depth2() {
        final TraceStatus status1 = trace.begin("Field1");
        final TraceStatus status2 = trace.begin("Field2");

        trace.end(status2);
        trace.end(status1);
    }


    @Test
    void begin_exception_depth2() {
        final TraceStatus status1 = trace.begin("Field1");
        final TraceStatus status2 = trace.begin("Field2");

        trace.exception(status2, new RuntimeException("error2"));
        trace.exception(status1, new RuntimeException("error1"));
    }
}
