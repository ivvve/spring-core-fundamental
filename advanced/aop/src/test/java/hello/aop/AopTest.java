package hello.aop;

import hello.aop.order.OrderRepository;
import hello.aop.order.OrderService;
import hello.aop.order.aop.AspectV5Order;
import hello.aop.order.aop.AspectV6Advice;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
//@Import(AspectV1.class)
//@Import(AspectV2.class)
//@Import(AspectV3.class)
//@Import(AspectV4Pointcut.class)
//@Import({AspectV5Order.LogAspect.class, AspectV5Order.TransactionAspect.class})
@Import(AspectV6Advice.class)
@SpringBootTest
public class AopTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void aopInfo() {
        log.info("isAopProxy, orderService={}", AopUtils.isAopProxy(orderService));
        log.info("isAopProxy, orderRepository={}", AopUtils.isAopProxy(orderRepository));

        /**
         * 2022-03-06 15:16:52.059  INFO 17982 --- [           main] hello.aop.AopTest                        : isAopProxy, orderService=true
         * 2022-03-06 15:16:52.059  INFO 17982 --- [           main] hello.aop.AopTest                        : isAopProxy, orderRepository=false
         */
    }

    @Test
    void success() {
        orderService.orderItem("itemA");

        /**
         * 2022-03-06 15:16:52.035  INFO 17982 --- [           main] hello.aop.order.aop.AspectV6Advice       : [트랜잭션 시작] void hello.aop.order.OrderService.orderItem(String)
         * 2022-03-06 15:16:52.036  INFO 17982 --- [           main] hello.aop.order.aop.AspectV6Advice       : [before] void hello.aop.order.OrderService.orderItem(String)
         * 2022-03-06 15:16:52.047  INFO 17982 --- [           main] hello.aop.order.OrderService             : [orderService] 실행
         * 2022-03-06 15:16:52.047  INFO 17982 --- [           main] hello.aop.order.OrderRepository          : [orderRepository] 실행
         * 2022-03-06 15:16:52.047  INFO 17982 --- [           main] hello.aop.order.aop.AspectV6Advice       : [return] void hello.aop.order.OrderService.orderItem(String) return=null
         * 2022-03-06 15:16:52.047  INFO 17982 --- [           main] hello.aop.order.aop.AspectV6Advice       : [after] void hello.aop.order.OrderService.orderItem(String)
         * 2022-03-06 15:16:52.047  INFO 17982 --- [           main] hello.aop.order.aop.AspectV6Advice       : [트랜잭션 커밋] void hello.aop.order.OrderService.orderItem(String)
         * 2022-03-06 15:16:52.047  INFO 17982 --- [           main] hello.aop.order.aop.AspectV6Advice       : [리소스 릴리즈] void hello.aop.order.OrderService.orderItem(String)
         */
    }

    @Test
    void exception() {
        assertThrows(
                IllegalStateException.class,
                () -> orderService.orderItem("ex")
        );

        /**
         * 2022-03-06 15:16:52.066  INFO 17982 --- [           main] hello.aop.order.aop.AspectV6Advice       : [트랜잭션 시작] void hello.aop.order.OrderService.orderItem(String)
         * 2022-03-06 15:16:52.066  INFO 17982 --- [           main] hello.aop.order.aop.AspectV6Advice       : [before] void hello.aop.order.OrderService.orderItem(String)
         * 2022-03-06 15:16:52.066  INFO 17982 --- [           main] hello.aop.order.OrderService             : [orderService] 실행
         * 2022-03-06 15:16:52.066  INFO 17982 --- [           main] hello.aop.order.OrderRepository          : [orderRepository] 실행
         * 2022-03-06 15:16:52.066  INFO 17982 --- [           main] hello.aop.order.aop.AspectV6Advice       : [ex] void hello.aop.order.OrderService.orderItem(String) message=예외 발생!
         * 2022-03-06 15:16:52.066  INFO 17982 --- [           main] hello.aop.order.aop.AspectV6Advice       : [after] void hello.aop.order.OrderService.orderItem(String)
         * 2022-03-06 15:16:52.066  INFO 17982 --- [           main] hello.aop.order.aop.AspectV6Advice       : [트랜잭션 롤백] void hello.aop.order.OrderService.orderItem(String)
         * 2022-03-06 15:16:52.066  INFO 17982 --- [           main] hello.aop.order.aop.AspectV6Advice       : [리소스 릴리즈] void hello.aop.order.OrderService.orderItem(String)
         */
    }
}
