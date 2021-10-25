package com.example.order.service;

import com.example.member.domain.Grade;
import com.example.member.domain.Member;
import com.example.member.service.MemberService;
import com.example.member.service.MemberServiceImpl;
import com.example.order.domain.Order;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        // given
        final MemberService memberService = new MemberServiceImpl();
        final OrderService orderService = new OrderServiceImpl();

        final Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        // when
        final Order order = orderService.createOrder(member.getId(), "itemA", BigInteger.valueOf(10_000));

        // then
        assertThat(order.getDiscountAmount()).isEqualTo(BigInteger.valueOf(1_000));
    }
}
