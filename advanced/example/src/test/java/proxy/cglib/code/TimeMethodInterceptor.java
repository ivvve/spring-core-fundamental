package proxy.cglib.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
@RequiredArgsConstructor
public class TimeMethodInterceptor implements MethodInterceptor {
    private final Object target;

    /**
     * @param o : CGLIB가 적용된 객체
     * @param method : 호출된 메서드
     * @param args : 메서드를 호출하면서 전달된 인수
     * @param methodProxy : 메서드 호출에 사용
     */
    @Override
    public Object intercept(final Object o, final Method method,
                            final Object[] args, final MethodProxy methodProxy) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

        Object result = methodProxy.invoke(target, args); // 실제 대상 호출

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("TimeProxy 종료 resultTime={}", resultTime);
        return result;
    }
}
