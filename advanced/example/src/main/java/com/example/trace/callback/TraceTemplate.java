package com.example.trace.callback;

import com.example.trace.LogTrace;
import com.example.trace.TraceStatus;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TraceTemplate {
    private final LogTrace trace;

    public <T> T execute(final String message, final TraceCallback<T> callback) {
        final TraceStatus status = this.trace.begin(message);

        try {
            final T result = callback.call();

            this.trace.end(status);
            return result;
        } catch (final Exception e) {
            this.trace.exception(status, e);
            throw e;
        }
    }
}
