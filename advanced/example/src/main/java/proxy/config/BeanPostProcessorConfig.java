package proxy.config;

import com.example.trace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import proxy.config.advice.LogTraceAdvice;
import proxy.config.postprocessor.PackageLogTraceProxyPostProcessor;

@Slf4j
//@Configuration
public class BeanPostProcessorConfig {
    @Bean
    public PackageLogTraceProxyPostProcessor logTraceProxyPostProcessor(final LogTrace logTrace) {
        return new PackageLogTraceProxyPostProcessor(
                "proxy.app",
                this.getAdvisor(logTrace)
        );
    }

    private Advisor getAdvisor(final LogTrace logTrace) {
        final NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");

        final LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut, logTraceAdvice);
    }
}
