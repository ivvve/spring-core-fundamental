package proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TimeAdvice implements MethodInterceptor {
    @Override
    public Object invoke(final MethodInvocation invocation) throws Throwable {
        log.info("TImeProxy 실행");
        long startTime = System.currentTimeMillis();

        // target 클래스의 정보는 MethodInvocation 안에 모두 포함되어 있다
        Object result = invocation.proceed(); // 실제 대상 호출

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("TimeProxy 종료 resultTime={}", resultTime);
        return result;
    }
}
