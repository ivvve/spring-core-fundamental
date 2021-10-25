package com.example.order.domain;

import java.math.BigInteger;

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

    public BigInteger calculatePrice() {
        return this.itemPrice.subtract(this.discountAmount);
    }

    public Long getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public BigInteger getItemPrice() {
        return itemPrice;
    }

    public BigInteger getDiscountAmount() {
        return discountAmount;
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
