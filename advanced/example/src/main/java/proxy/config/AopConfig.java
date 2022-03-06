package proxy.config;

import com.example.trace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import proxy.config.aspect.LogTraceAspect;

@Configuration
public class AopConfig {
    @Bean
    public LogTraceAspect logTraceAspect(final LogTrace logTrace) {
        return new LogTraceAspect(logTrace);
    }
}
