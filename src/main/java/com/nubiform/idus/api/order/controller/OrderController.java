package com.nubiform.idus.api.order.controller;

import com.nubiform.idus.api.order.model.MemberOrder;
import com.nubiform.idus.api.order.model.Order;
import com.nubiform.idus.api.order.service.OrderService;
import com.nubiform.idus.config.response.IdusErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order")
@Tag(name = "Order", description = "오더관련 api")
@SecurityRequirement(name = "Authorization")
@ApiResponse(responseCode = "200", description = "OK")
@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = IdusErrorResponse.class)))
@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = IdusErrorResponse.class)))
@ApiResponse(responseCode = "500", description = "Error Message", content = @Content(schema = @Schema(implementation = IdusErrorResponse.class)))
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orders")
    @Operation(summary = "주문목록조회", description = "주문 목록을 조회합니다.",
            parameters = {@Parameter(name = "id", description = "회원아이디")})
    public List<Order> getOrders(@RequestParam(required = false) String id) {
        return orderService.getOrders(id);
    }

    @GetMapping("/member-orders")
    @Operation(summary = "회원주문목록조회", description = "회원 주문 목록을 조회합니다.",
            parameters = {@Parameter(name = "name", description = "회원이름"),
                    @Parameter(name = "email", description = "회원이메일"),
                    @Parameter(name = "page", description = "페이지"),
                    @Parameter(name = "size", description = "조회사이즈")})
    public List<MemberOrder> getMemberOrders(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        return orderService.getMemberOrders(name, email, PageRequest.of(page, size));
    }
}
