package proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BasicTest {
    @Test
    void basicConfig() {
        final AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(BasicConfig.class);

        final A a = ac.getBean(A.class);
        a.helloA();

        assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean(B.class)
        );
    }

    @Configuration
    static class BasicConfig {
        @Bean(name = "beanA")
        public A a() {
            return new A();
        }
    }

    @Slf4j
    static class A {
        public void helloA() {
            log.info("Hello, A");
        }
    }

    @Slf4j
    static class B {
        public void helloB() {
            log.info("Hello, B");
        }
    }
}
