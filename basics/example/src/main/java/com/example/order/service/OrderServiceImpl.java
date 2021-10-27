package com.example.order.service;

import com.example.discount.domain.DiscountPolicy;
import com.example.member.domain.Member;
import com.example.member.repository.MemberRepository;
import com.example.order.domain.Order;

import java.math.BigInteger;

public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(final MemberRepository memberRepository, final DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(final Long memberId, final String itemName, final BigInteger itemPrice) {
        final Member member = this.memberRepository.findById(memberId)
                .orElseThrow();
        final BigInteger discountAmount = this.discountPolicy.discount(member, itemPrice);

        return new Order(1L, itemName, itemPrice, discountAmount);
    }
}
