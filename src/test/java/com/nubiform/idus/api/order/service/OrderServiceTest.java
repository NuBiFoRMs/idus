package com.nubiform.idus.api.order.service;

import com.nubiform.idus.api.order.repository.OrderMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

class OrderServiceTest {

    private OrderService orderService;

    @MockBean
    private OrderMapper orderMapper;

    @BeforeEach
    void before() {
        this.orderService = new OrderService(orderMapper);
    }

    @Test
    @DisplayName("단일 회원의 주문 목록 조회")
    void getOrders() {

    }

    @Test
    @DisplayName("여러 회원 목록 조회")
    void getMemberOrders() {

    }
}
