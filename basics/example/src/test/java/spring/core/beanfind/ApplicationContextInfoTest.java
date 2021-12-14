package spring.core.beanfind;

import com.example.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Objects;

class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @DisplayName("모든 빈 출력하기")
    @Test
    void findAllBeans() {
        final String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (final String beanDefinitionName : beanDefinitionNames) {
            final Object bean = ac.getBean(beanDefinitionName);
            System.out.println("beanDefinitionName = " + beanDefinitionName);
            System.out.println("bean = " + bean);
            System.out.println("==============================");
        }
    }

    @DisplayName("Application Bean 출력하기")
    @Test
    void findApplicationBean() {
        final String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (final String beanDefinitionName : beanDefinitionNames) {
            final BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            // Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
            // Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
            if (Objects.equals(beanDefinition.getRole(), BeanDefinition.ROLE_APPLICATION)) {
                final Object bean = ac.getBean(beanDefinitionName);
                System.out.println("beanDefinitionName = " + beanDefinitionName);
                System.out.println("bean = " + bean);
                System.out.println("==============================");
            }
        }
    }
}
