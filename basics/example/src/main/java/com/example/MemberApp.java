package com.example;

import com.example.member.domain.Grade;
import com.example.member.domain.Member;
import com.example.member.service.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        final AppConfig appConfig = new AppConfig();
//        final MemberService memberService = appConfig.memberService();
        final ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        final MemberService memberService = ac.getBean(MemberService.class);

        final Member newMember = new Member(1L, "memberA", Grade.VIP);
        memberService.join(newMember);

        final Member foundMember = memberService.findMember(1L);
        System.out.println("new member = " + newMember.getName());
        System.out.println("found member = " + foundMember.getName());
    }
}
