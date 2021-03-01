package com.nubiform.idus.api.order.controller;

import com.nubiform.idus.IdusResponse;
import com.nubiform.idus.api.order.model.Order;
import com.nubiform.idus.api.order.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order")
@Tag(name = "Order", description = "오더관련 api")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return orderService.getOrders();
    }
}
