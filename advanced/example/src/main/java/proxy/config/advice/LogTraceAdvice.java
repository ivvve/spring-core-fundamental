package proxy.config.advice;

import com.example.trace.LogTrace;
import com.example.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

@Slf4j
@RequiredArgsConstructor
public class LogTraceAdvice implements MethodInterceptor {
    private final LogTrace logTrace;

    @Override
    public Object invoke(final MethodInvocation invocation) throws Throwable {
        final Method method = invocation.getMethod();
        final String message = this.getMessage(method);

        final TraceStatus status = logTrace.begin(message);

        try {
            // 로직 호출
            final Object result = invocation.proceed();

            this.logTrace.end(status);

            return result;
        } catch (Exception e) {
            this.logTrace.exception(status, e);
            throw e;
        }
    }

    private String getMessage(final Method method) {
        return new StringBuilder()
                .append(method.getDeclaringClass().getSimpleName())
                .append(".")
                .append(method.getName())
                .append("()")
                .toString();
    }
}
