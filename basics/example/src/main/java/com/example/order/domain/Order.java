package com.example.order.domain;

import lombok.Getter;

import java.math.BigInteger;

@Getter
public class Order {
    private Long id;
    private String itemName;
    private BigInteger itemPrice;
    private BigInteger discountAmount;

    public Order(final Long id, final String itemName, final BigInteger itemPrice, final BigInteger discountAmount) {
        this.id = id;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discountAmount = discountAmount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", discountPrice=" + discountAmount +
                '}';
    }
}
