package com.example;

import com.example.member.domain.Grade;
import com.example.member.domain.Member;
import com.example.member.service.MemberService;
import com.example.member.service.MemberServiceImpl;
import com.example.order.domain.Order;
import com.example.order.service.OrderService;
import com.example.order.service.OrderServiceImpl;

import java.math.BigInteger;

public class OrderApp {
    public static void main(String[] args) {
        final MemberService memberService = new MemberServiceImpl();
        final OrderService orderService = new OrderServiceImpl();

        final Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        final Order order = orderService.createOrder(member.getId(), "itemA", BigInteger.valueOf(10_000));
        System.out.println(order);
    }
}
