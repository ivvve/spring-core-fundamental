package com.example.discount.domain;

import com.example.member.domain.Member;

import java.math.BigInteger;

public interface DiscountPolicy {
    /**
     * @return 할인 금액
     */
    BigInteger discount(Member member, BigInteger price);
}
