package com.example;

import com.example.discount.domain.RateDiscountPolicy;
import com.example.member.repository.MemberRepository;
import com.example.member.repository.MemoryMemberRepository;
import com.example.member.service.MemberService;
import com.example.member.service.MemberServiceImpl;
import com.example.order.service.OrderService;
import com.example.order.service.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(
                this.memberRepository()
        );
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(
                this.memberRepository(),
                this.discountPolicy()
        );
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public RateDiscountPolicy discountPolicy() {
//        return new FixedDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
