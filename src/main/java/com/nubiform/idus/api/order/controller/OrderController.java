package com.nubiform.idus.api.order.controller;

import com.nubiform.idus.api.order.model.MemberOrder;
import com.nubiform.idus.api.order.model.Order;
import com.nubiform.idus.api.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order")
@Tag(name = "Order", description = "오더관련 api")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orders")
    @Operation(summary = "주문목록조회", description = "주문 목록을 조회합니다.", parameters = {@Parameter(name = "id", description = "회원아이디")})
    public List<Order> getOrders(@RequestParam(required = false) String id) {
        return orderService.getOrders(id);
    }

    @GetMapping("/member-orders")
    @Operation(summary = "회원주문목록조회", description = "회원 주문 목록을 조회합니다.", parameters = {@Parameter(name = "name", description = "회원이름"), @Parameter(name = "email", description = "회원이메일")})
    public List<MemberOrder> getMemberOrders(@RequestParam(required = false) String name, @RequestParam(required = false) String email) {
        return orderService.getMemberOrders(name, email);
    }
}
