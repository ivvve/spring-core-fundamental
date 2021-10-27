package com.example.discount.domain;

import com.example.member.domain.Grade;
import com.example.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class RateDiscountPolicyTest {
    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @DisplayName("VIP는 10% 할인이 적용되어야한다")
    @Test
    void when_vip() {
        // given
        final Member member = new Member(1L, "vip", Grade.VIP);

        // when
        final BigInteger discountAmount = rateDiscountPolicy.discount(member, BigInteger.valueOf(10_000));

        // then
        assertThat(discountAmount).isEqualTo(1_000);
    }


    @DisplayName("VIP가 아니면 할인이 적용되지않는다")
    @Test
    void when_not_vip() {
        // given
        final Member member = new Member(1L, "vip", Grade.BASIC);

        // when
        final BigInteger discountAmount = rateDiscountPolicy.discount(member, BigInteger.valueOf(10_000));

        // then
        assertThat(discountAmount).isEqualTo(0);
    }
}
