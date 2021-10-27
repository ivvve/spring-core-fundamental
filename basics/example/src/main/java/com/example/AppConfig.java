package com.example;

import com.example.discount.domain.RateDiscountPolicy;
import com.example.member.repository.MemoryMemberRepository;
import com.example.member.service.MemberService;
import com.example.member.service.MemberServiceImpl;
import com.example.order.service.OrderService;
import com.example.order.service.OrderServiceImpl;

public class AppConfig {
    public MemberService memberService() {
        return new MemberServiceImpl(
                new MemoryMemberRepository()
        );
    }

    public OrderService orderService() {
        return new OrderServiceImpl(
                new MemoryMemberRepository(),
                new RateDiscountPolicy()
        );
    }
}
