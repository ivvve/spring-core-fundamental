package com.example.discount.domain;

import com.example.member.domain.Member;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class RateDiscountPolicy implements DiscountPolicy {
    private static final int DISCOUNT_PERCENT = 10;

    @Override
    public BigInteger discount(final Member member, final BigInteger price) {
        if (member.isVip()) {
            final BigDecimal discountAmount = BigDecimal.valueOf(price.longValueExact())
                    .multiply(BigDecimal.valueOf(DISCOUNT_PERCENT))
                    .divide(BigDecimal.valueOf(100), RoundingMode.DOWN);

            return discountAmount.toBigInteger();
        }

        return BigInteger.ZERO;
    }
}
