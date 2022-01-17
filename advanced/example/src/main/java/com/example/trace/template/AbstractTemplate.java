package com.example.trace.template;

import com.example.trace.LogTrace;
import com.example.trace.TraceStatus;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractTemplate<T> {
    private final LogTrace trace;

    public T execute(final String message) {
        final TraceStatus status = this.trace.begin(message);

        try {
            final T result = this.call();

            this.trace.end(status);
            return result;
        } catch (final Exception e) {
            this.trace.exception(status, e);
            throw e;
        }
    }

    protected abstract T call();
}
