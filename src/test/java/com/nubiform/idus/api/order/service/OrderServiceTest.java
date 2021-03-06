package com.nubiform.idus.api.order.service;

import com.nubiform.idus.api.order.repository.OrderMapper;
import org.junit.jupiter.api.BeforeEach;
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
    void getOrders() {

    }

    @Test
    void getMemberOrders() {

    }
}
