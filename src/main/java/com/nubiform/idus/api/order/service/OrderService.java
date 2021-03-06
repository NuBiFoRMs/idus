package com.nubiform.idus.api.order.service;

import com.nubiform.idus.api.order.model.MemberOrder;
import com.nubiform.idus.api.order.model.Order;
import com.nubiform.idus.api.order.repository.OrderMapper;
import com.nubiform.idus.config.error.IdusException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderMapper orderMapper;

    @Transactional(readOnly = true)
    public List<Order> getOrders(String memberId) {
        List<Order> orders = orderMapper.getOrders(memberId);

        if (orders.size() == 0) {
            throw IdusException.of("no data");
        }

        return orders;
    }

    @Transactional(readOnly = true)
    public List<MemberOrder> getMemberOrders(String memberName, String email, Pageable pageable) {
        List<MemberOrder> memberOrders = orderMapper.getMemberOrders(memberName, email, pageable);

        if (memberOrders.size() == 0) {
            throw IdusException.of("no data");
        }

        return memberOrders;
    }
}
