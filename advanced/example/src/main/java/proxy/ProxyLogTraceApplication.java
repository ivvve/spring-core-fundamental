package proxy;

import com.example.trace.LogTrace;
import com.example.trace.ThreadLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import proxy.config.AppV1ProxyFactoryConfig;
import proxy.config.AppV2Config;
import proxy.config.AppV2ProxyFactoryConfig;

@Import({AppV1ProxyFactoryConfig.class, AppV2ProxyFactoryConfig.class})
@SpringBootApplication
public class ProxyLogTraceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxyLogTraceApplication.class, args);
    }

    @Bean
    public LogTrace logTrace() {
        return new ThreadLogTrace();
    }
}
