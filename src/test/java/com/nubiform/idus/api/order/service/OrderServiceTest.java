package com.nubiform.idus.api.order.service;

import com.nubiform.idus.api.order.model.MemberOrder;
import com.nubiform.idus.api.order.model.Order;
import com.nubiform.idus.api.order.repository.OrderMapper;
import com.nubiform.idus.config.error.IdusException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
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
        List<Order> orderList = new ArrayList<>();
        Order order = new Order();
        order.setOrderNumber("101");
        order.setMemberId("user");
        orderList.add(order);
        order = new Order();
        order.setOrderNumber("102");
        order.setMemberId("user");
        orderList.add(order);

        when(orderMapper.getOrders("user")).thenReturn(orderList);

        assertEquals(2, orderService.getOrders("user").size());
        assertEquals("user", orderService.getOrders("user").get(0).getMemberId());
        assertEquals("user", orderService.getOrders("user").get(1).getMemberId());
    }

    @Test
    @DisplayName("단일 회원의 주문 목록 조회 예외상")
    void getOrdersException() {
        when(orderMapper.getOrders("user")).thenReturn(new ArrayList<>());

        IdusException exception = assertThrows(IdusException.class, () -> orderService.getOrders("user"));

        verify(orderMapper, times(1)).getOrders("user");
    }

    @Test
    @DisplayName("여러 회원 목록 조회")
    void getMemberOrders() {
        List<MemberOrder> memberOrderList01 = new ArrayList<>();
        MemberOrder memberOrder = new MemberOrder();
        memberOrder.setOrderNumber("101");
        memberOrder.setMemberName("user01");
        memberOrder.setEmail("user01@idus.com");
        memberOrderList01.add(memberOrder);
        List<MemberOrder> memberOrderList02 = new ArrayList<>();
        memberOrder = new MemberOrder();
        memberOrder.setOrderNumber("102");
        memberOrder.setMemberName("user02");
        memberOrder.setEmail("user02@idus.com");
        memberOrderList02.add(memberOrder);

        when(orderMapper.getMemberOrders("user01", "user01@idus.com", PageRequest.of(1, 10))).thenReturn(memberOrderList01);
        when(orderMapper.getMemberOrders("user02", "user02@idus.com", PageRequest.of(1, 10))).thenReturn(memberOrderList02);

        List<MemberOrder> result01 = orderService.getMemberOrders("user01", "user01@idus.com", PageRequest.of(1, 10));
        List<MemberOrder> result02 = orderService.getMemberOrders("user02", "user02@idus.com", PageRequest.of(1, 10));

        assertEquals("user01", result01.get(0).getMemberName());
        assertEquals(1, result01.size());
        assertEquals("user02", result02.get(0).getMemberName());
        assertEquals(1, result02.size());

        verify(orderMapper, times(1)).getMemberOrders("user01", "user01@idus.com", PageRequest.of(1, 10));
        verify(orderMapper, times(1)).getMemberOrders("user02", "user02@idus.com", PageRequest.of(1, 10));
    }

    @Test
    @DisplayName("여러 회원 목록 조회 예외상황")
    void getMemberOrdersException() {
        when(orderMapper.getMemberOrders(null, null, null)).thenReturn(new ArrayList<>());

        IdusException exception = assertThrows(IdusException.class, () -> orderService.getMemberOrders(null, null, null));

        verify(orderMapper, times(1)).getMemberOrders(null, null, null);
    }
}
