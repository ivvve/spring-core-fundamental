package spring.core.beanfind;

import com.example.AppConfig;
import com.example.member.service.MemberService;
import com.example.member.service.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @DisplayName("빈 이름으로 조회")
    @Test
    void findBeanByName() {
        final Object memberService = ac.getBean("memberService");

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @DisplayName("타입으로 조회")
    @Test
    void findBeanByType() {
        final MemberService memberService = ac.getBean(MemberService.class);

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @DisplayName("빈 이름, 인터페이스 타입으로 조회")
    @Test
    void findBeanByNameAndType1() {
        final MemberService memberService = ac.getBean("memberService", MemberService.class);

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @DisplayName("빈 이름, 구체 타입으로 조회")
    @Test
    void findBeanByNameAndType2() {
        final MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @DisplayName("빈 없을 때 NoSuchBeanDefinitionException")
    @Test
    void noSuchBeanDefinitionException() {
        assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxx", MemberService.class)
        );
    }
}
