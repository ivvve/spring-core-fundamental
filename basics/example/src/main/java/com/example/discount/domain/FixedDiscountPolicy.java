package com.example.discount.domain;

import com.example.member.domain.Member;

import java.math.BigInteger;

public class FixedDiscountPolicy implements DiscountPolicy {
    private static final BigInteger DISCOUNT_AMOUNT = BigInteger.valueOf(1_000);

    @Override
    public BigInteger discount(final Member member, final BigInteger price) {
        if (member.isVip()) {
            return DISCOUNT_AMOUNT;
        }

        return BigInteger.ZERO;
    }
}
