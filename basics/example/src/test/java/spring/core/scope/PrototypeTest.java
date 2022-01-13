package spring.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {
    @Test
    void prototypeBeanFind() {
        final AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        final PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        final PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println(prototypeBean1);
        System.out.println(prototypeBean2);
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        ac.close();
    }


    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean#init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean#destory");
        }
    }
}
