package spring.core.lifecycle;

import com.example.lifecycle.NetworkClient3;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
//    @Test
//    void lifeCycleTest() {
//        var ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
//        System.out.println(">> Spring Container 생성");
//        var networkClient = ac.getBean(NetworkClient.class);
//        System.out.println(">> NetworkClient Bean 조회");
//        ac.close(); // Spring Container 종료
//        System.out.println(">> Spring Container 종료");
//    }

//    @Test
//    void lifeCycleTest2() {
//        var ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
//        System.out.println(">> Spring Container 생성");
//        var networkClient = ac.getBean(NetworkClient2.class);
//        System.out.println(">> NetworkClient Bean 조회");
//        ac.close(); // Spring Container 종료
//        System.out.println(">> Spring Container 종료");
//    }

    @Test
    void lifeCycleTest3() {
        var ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        System.out.println(">> Spring Container 생성");
        var networkClient = ac.getBean(NetworkClient3.class);
        System.out.println(">> NetworkClient Bean 조회");
        ac.close(); // Spring Container 종료
        System.out.println(">> Spring Container 종료");
    }

    @Configuration
    static class LifeCycleConfig {
//        @Bean
//        public NetworkClient networkClient() {
//            NetworkClient networkClient = new NetworkClient();
//            networkClient.setUrl("http://hello-spring.dev");
//            return networkClient;
//        }

//        @Bean(initMethod = "init", destroyMethod = "close")
//        public NetworkClient2 networkClient() {
//            NetworkClient2 networkClient = new NetworkClient2();
//            networkClient.setUrl("http://hello-spring.dev");
//            return networkClient;
//        }

        @Bean
        public NetworkClient3 networkClient() {
            NetworkClient3 networkClient = new NetworkClient3();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
