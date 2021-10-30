package com.example.trace;

import lombok.Getter;

import java.util.UUID;

@Getter
public class TraceId {
    private static int FIRST_DEPTH = 0;

    private final String id;
    private final int depth;

    public TraceId() {
        this.id = this.createId();
        this.depth = FIRST_DEPTH;
    }

    private TraceId(final String id, final int depth) {
        this.id = id;
        this.depth = depth;
    }

    private String createId() {
        final String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 8);
    }

    public TraceId next() {
        return new TraceId(this.id, this.depth + 1);
    }

    public TraceId previous() {
        return new TraceId(this.id, this.depth - 1);
    }

    public boolean isFirstDepth() {
        return this.depth == FIRST_DEPTH;
    }
}
