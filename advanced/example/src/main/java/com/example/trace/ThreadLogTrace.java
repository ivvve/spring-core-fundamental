package com.example.trace;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
public class ThreadLogTrace implements LogTrace {
    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

//    private TraceId traceIdHolder; //traceId 동기화, 동시성 이슈 발생
    private ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<>();

    @Override
    public TraceStatus begin(final String message) {
        this.syncTraceId();

        final TraceId traceId = this.traceIdHolder.get();
        final TraceStatus traceStatus = new TraceStatus(traceId, Instant.now(), message);

        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getDepth()), message);
        return traceStatus;
    }

    @Override
    public void end(final TraceStatus status) {
        this.complete(status, null);
    }

    @Override
    public void exception(final TraceStatus status, final Exception e) {
        this.complete(status, e);
    }

    private void complete(final TraceStatus status, final Exception e) {
        final Instant endTime = Instant.now();
        final long durationTimeMs = Duration.between(status.getStartTime(), endTime).toMillis();

        final TraceId traceId = status.getTraceId();

        // Error log
        if (nonNull(e)) {
            log.info("[{}] {}{} time={}ms ex={}",
                    traceId.getId(), addSpace(EX_PREFIX, traceId.getDepth()), status.getMessage(),
                    durationTimeMs, e.toString());
            return;
        } else {
            // Normal Complete Log
            log.info("[{}] {}{} time={}ms", traceId.getId(),
                    addSpace(COMPLETE_PREFIX, traceId.getDepth()), status.getMessage(), durationTimeMs);
        }

        this.releaseTraceId();
    }

    private void syncTraceId() {
        final TraceId traceId = traceIdHolder.get();

        if (isNull(traceId)) {
            this.traceIdHolder.set(new TraceId());
        } else {
            this.traceIdHolder.set(traceId.next());
        }
    }

    private void releaseTraceId() {
        final TraceId traceId = this.traceIdHolder.get();

        if (traceId.isFirstDepth()) {
            this.traceIdHolder.remove();
        } else {
            this.traceIdHolder.set(traceId.previous());
        }
    }

    private static String addSpace(final String prefix, final int depth) {
        final StringBuilder builder = new StringBuilder();

        // 로그의 깊이를 시각화해준다
        for (int i = 0; i < depth; i++) {
            if (i == (depth - 1)) {
                builder.append("|").append(prefix);
            } else {
                builder.append("|\t");
            }
        }

        return builder.toString();
    }
}
