package proxy.config.aspect;

import com.example.trace.LogTrace;
import com.example.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

@RequiredArgsConstructor
public class LogTraceAspect {
    private final LogTrace logTrace;

    @Around("execution(* proxy.app..*(..))")
    public Object execute(final ProceedingJoinPoint joinPoint) throws Throwable {
        final String message = joinPoint.getSignature().toShortString();
        final TraceStatus status = logTrace.begin(message);

        try {
            // 로직 호출
            final Object result = joinPoint.proceed();

            this.logTrace.end(status);

            return result;
        } catch (Exception e) {
            this.logTrace.exception(status, e);
            throw e;
        }
    }
}
