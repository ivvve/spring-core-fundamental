package spring.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

class PrototypeProviderTest {
    @Test
    void singletonClientUsePrototype() {
        var ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        var clientBean1 = ac.getBean(ClientBean.class);
        var count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        var clientBean2 = ac.getBean(ClientBean.class);
        var count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(2);
    }


    static class ClientBean {
        private final PrototypeBean prototypeBean;

        public ClientBean(final PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public int logic() {
            this.prototypeBean.addCount();
            return this.prototypeBean.count;
        }
    }

    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    static class PrototypeBean {
        public int count = 0;

        public void addCount() {
            this.count++;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean#init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean#destory");
        }
    }
}
