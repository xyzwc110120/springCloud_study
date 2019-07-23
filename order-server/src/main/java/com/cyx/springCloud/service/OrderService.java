package com.cyx.springCloud.service;

public interface OrderService {

    void placeOrder(Long memberId, Long productId);
}
