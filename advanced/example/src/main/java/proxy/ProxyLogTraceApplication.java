package proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import proxy.config.AppV1Config;
import proxy.config.AppV2Config;

@Import({AppV1Config.class, AppV2Config.class})
@SpringBootApplication
public class ProxyLogTraceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxyLogTraceApplication.class, args);
    }
}