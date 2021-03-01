package com.nubiform.idus.api.order.service;

import com.nubiform.idus.api.order.model.Order;
import com.nubiform.idus.api.order.repository.OrderMapper;
import com.nubiform.idus.config.error.IdusException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderMapper orderMapper;

    public List<Order> getOrders() {
        List<Order> orders = orderMapper.getOrders();

        if (orders.size() == 0) {
            throw IdusException.of("no data");
        }

        return orders;
    }
}
