package com.nubiform.idus.api.order.repository;

import com.nubiform.idus.api.order.model.MemberOrder;
import com.nubiform.idus.api.order.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {
    public List<Order> getOrders(String memberId);
    public List<MemberOrder> getMemberOrders(String memberName, String email);
}