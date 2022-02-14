package proxy.config;

import com.example.trace.LogTrace;
import com.example.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.util.PatternMatchUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@RequiredArgsConstructor
public class LogTraceFilterHandler implements InvocationHandler {
    private final Object target; // Proxy가 호출할 대상
    private final LogTrace logTrace;
    private final String[] patterns; // Log를 남길 대상

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        if (this.shouldSkipLog(method.getName())) {
            return method.invoke(target, args);
        }

        final String message = this.getMessage(method);
        final TraceStatus status = logTrace.begin(message);

        try {
            // 로직 호출
            final Object result = method.invoke(target, args);

            this.logTrace.end(status);

            return result;
        } catch (Exception e) {
            this.logTrace.exception(status, e);
            throw e;
        }
    }

    private boolean shouldSkipLog(final String methodName) {
        return !PatternMatchUtils.simpleMatch(this.patterns, methodName);
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
