package spring.core.container;

import com.example.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ByteManipulatingTest {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    void configurationDeep() {
        final AppConfig appConfig = ac.getBean(AppConfig.class);
        System.out.println("appConfig = " + appConfig.getClass());
    }
}
