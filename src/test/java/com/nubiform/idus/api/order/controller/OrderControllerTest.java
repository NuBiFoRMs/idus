package com.nubiform.idus.api.order.controller;

import com.nubiform.idus.AbstractControllerTest;
import com.nubiform.idus.api.order.model.MemberOrder;
import com.nubiform.idus.api.order.model.Order;
import com.nubiform.idus.api.order.repository.OrderMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OrderControllerTest extends AbstractControllerTest {

    @Autowired
    private OrderController orderController;

    @Override
    protected Object controller() {
        return orderController;
    }

    @MockBean
    private OrderMapper orderMapper;

    @Test
    @DisplayName("URI: /api/v1/order/orders")
    void getOrders() throws Exception {
        List<Order> orderList = new ArrayList<>();
        Order order = new Order();
        order.setMemberId("user");
        orderList.add(order);
        when(orderMapper.getOrders("user")).thenReturn(orderList);

        String token = jwtTokenProvider.createToken("user");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "user");
        mockMvc.perform(get("/api/v1/order/orders")
                .header("Authorization", "Bearer " + token)
                .params(params))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].memberId").value("user"));

        verify(orderMapper, times(1)).getOrders("user");
    }

    @Test
    @DisplayName("URI: /api/v1/order/orders Exception")
    void getOrdersException() throws Exception {
        when(orderMapper.getOrders("user")).thenReturn(new ArrayList<>());

        String token = jwtTokenProvider.createToken("user");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "user");
        mockMvc.perform(get("/api/v1/order/orders")
                .header("Authorization", "Bearer " + token)
                .params(params))
                .andExpect(status().isInternalServerError());

        verify(orderMapper, times(1)).getOrders("user");
    }

    @Test
    @DisplayName("URI: /api/v1/order/orders : Unauthorized")
    void getOrdersUnauthorized() throws Exception {
        mockMvc.perform(get("/api/v1/order/orders"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("URI: /api/v1/order/member-orders")
    void getMemberOrders() throws Exception {
        List<MemberOrder> memberOrderList = new ArrayList<>();
        MemberOrder memberOrder = new MemberOrder();
        memberOrder.setMemberName("username");
        memberOrderList.add(memberOrder);
        when(orderMapper.getMemberOrders("username", null, PageRequest.of(1, 10))).thenReturn(memberOrderList);

        String token = jwtTokenProvider.createToken("user");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("name", "username");
        mockMvc.perform(get("/api/v1/order/member-orders")
                .header("Authorization", "Bearer " + token)
                .params(params))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].memberName").value("username"));

        verify(orderMapper, times(1)).getMemberOrders("username", null, PageRequest.of(1, 10));
    }

    @Test
    @DisplayName("URI: /api/v1/order/member-orders : Exception")
    void getMemberOrdersException() throws Exception {
        when(orderMapper.getMemberOrders("username", null, PageRequest.of(1, 10))).thenReturn(new ArrayList<>());

        String token = jwtTokenProvider.createToken("user");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("name", "username");
        mockMvc.perform(get("/api/v1/order/member-orders")
                .header("Authorization", "Bearer " + token)
                .params(params))
                .andExpect(status().isInternalServerError());

        verify(orderMapper, times(1)).getMemberOrders("username", null, PageRequest.of(1, 10));
    }

    @Test
    @DisplayName("URI: /api/v1/order/member-orders : Unauthorized")
    void getMembersUnauthorized() throws Exception {
        mockMvc.perform(get("/api/v1/order/member-orders"))
                .andExpect(status().isUnauthorized());
    }
}