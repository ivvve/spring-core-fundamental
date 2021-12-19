package com.example;

import com.example.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class AutoAppConfigTest {
    @Test
    void basicScan() {
        final ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        final MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
