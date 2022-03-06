package proxy.config;

import com.example.trace.LogTrace;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import proxy.config.advice.LogTraceAdvice;

@Configuration
public class AutoProxyConfig {
//    @Bean
    public Advisor advisor1(final LogTrace logTrace) {
        final NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");
        final LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut, logTraceAdvice);
    }

//    @Bean
    public Advisor advisor2(final LogTrace logTrace) {
        final AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* proxy.app..*(..))");
        final LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut, logTraceAdvice);
    }

    @Bean
    public Advisor advisor3(final LogTrace logTrace) {
        final AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* proxy.app..*(..)) && !execution(* proxy.app..noLog(..))");
        final LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut, logTraceAdvice);
    }
}
