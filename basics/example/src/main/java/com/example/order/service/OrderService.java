package com.example.order.service;

import com.example.order.domain.Order;

import java.math.BigInteger;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, BigInteger itemPrice);
}
