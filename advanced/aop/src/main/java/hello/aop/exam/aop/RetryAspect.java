package hello.aop.exam.aop;

import hello.aop.exam.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class RetryAspect {
    // @Retry 가 붙은 메서드에 Advice를 적용
    // @annotation(retry), 인자 Retry retry 를 사용해서 어드바이스에 애노테이션을 파라미터로 전달한다.
    @Around("@annotation(retry)")
    public Object doTrace(final ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
        log.info("[retry] {} retry={}", joinPoint.getSignature(), retry);

        final int maxRetry = retry.value();
        Throwable exceptionHolder = null;

        for (int retryCount = 1; retryCount <= maxRetry; retryCount++) {
            try {
                log.info("[retry] try count={}/{}", retryCount, maxRetry);
                return joinPoint.proceed();
            } catch (Throwable e) {
                exceptionHolder = e;
            }
        }

        throw exceptionHolder;
    }
}
