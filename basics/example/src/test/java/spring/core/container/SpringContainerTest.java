package spring.core.container;

import com.example.AppConfig;
import com.example.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SpringContainerTest {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @DisplayName("스프링 컨테이너와 싱글톤")
    @Test
    void springContainer() {
        final MemberService memberService1 = ac.getBean(MemberService.class);
        final MemberService memberService2 = ac.getBean(MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isSameAs(memberService2);
    }
}
