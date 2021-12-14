package spring.core.container;

import com.example.AppConfig;
import com.example.member.repository.MemberRepository;
import com.example.member.service.MemberService;
import com.example.order.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonBeanTest {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @DisplayName("스프링 컨테이너와 싱글톤")
    @Test
    void springContainer() {
        final MemberService memberService = ac.getBean(MemberService.class);
        final OrderService orderService = ac.getBean(OrderService.class);
        final MemberRepository memberRepository = ac.getBean(MemberRepository.class);

        final Object memberRepositoryOfMemberService = ReflectionTestUtils.getField(memberService, "memberRepository");
        final Object memberRepositoryOfOrderService = ReflectionTestUtils.getField(orderService, "memberRepository");

        System.out.println("memberRepositoryOfMemberService = " + memberRepositoryOfMemberService);
        System.out.println("memberRepositoryOfOrderService = " + memberRepositoryOfOrderService);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberRepositoryOfMemberService).isSameAs(memberRepositoryOfOrderService);
        assertThat(memberRepositoryOfMemberService).isSameAs(memberRepository);
    }
}
