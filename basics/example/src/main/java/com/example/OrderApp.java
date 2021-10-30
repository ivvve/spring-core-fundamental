package com.example;

import com.example.member.domain.Grade;
import com.example.member.domain.Member;
import com.example.member.service.MemberService;
import com.example.order.domain.Order;
import com.example.order.service.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigInteger;

public class OrderApp {
    public static void main(String[] args) {
//        final AppConfig appConfig = new AppConfig();
//        final MemberService memberService = appConfig.memberService();
//        final OrderService orderService = appConfig.orderService();
        final ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        final MemberService memberService = ac.getBean(MemberService.class);
        final OrderService orderService = ac.getBean(OrderService.class);

        final Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        final Order order = orderService.createOrder(member.getId(), "itemA", BigInteger.valueOf(10_000));
        System.out.println(order);
    }
}
