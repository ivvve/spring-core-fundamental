package com.example.trace;

import lombok.Getter;

import java.time.Instant;

@Getter
public class TraceStatus {
    private final TraceId traceId;
    private final Instant startTime;
    private final String message;

    public TraceStatus(final TraceId traceId, final Instant startTime, final String message) {
        this.traceId = traceId;
        this.startTime = startTime;
        this.message = message;
    }
}
