package com.example.trace.hello;

import com.example.trace.TraceId;
import com.example.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

import static java.util.Objects.nonNull;

@Slf4j
@Component
public class HelloTraceV2 {
    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    public TraceStatus begin(final String message) {
        final TraceId traceId = new TraceId();
        final TraceStatus traceStatus = new TraceStatus(traceId, Instant.now(), message);

        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getDepth()), message);
        return traceStatus;
    }

    // V2에서 추가
    public TraceStatus beginSync(final TraceId priorTraceId, final String message) {
        final TraceId nextTraceId = priorTraceId.next();
        log.info("[{}] {}{}", nextTraceId.getId(), addSpace(START_PREFIX, nextTraceId.getDepth()), message);
        return new TraceStatus(nextTraceId, Instant.now(), message);
    }

    public void end(TraceStatus status) {
        complete(status, null);
    }

    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }

    public void complete(final TraceStatus status, final Exception e) {
        final Instant endTime = Instant.now();
        final long durationTimeMs = Duration.between(status.getStartTime(), endTime).toMillis();

        final TraceId traceId = status.getTraceId();

        // Error log
        if (nonNull(e)) {
            log.info("[{}] {}{} time={}ms ex={}",
                    traceId.getId(), addSpace(EX_PREFIX, traceId.getDepth()), status.getMessage(),
                    durationTimeMs, e.toString());
            return;
        }


        // Normal Complete Log
        log.info("[{}] {}{} time={}ms", traceId.getId(),
                addSpace(COMPLETE_PREFIX, traceId.getDepth()), status.getMessage(), durationTimeMs);
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
