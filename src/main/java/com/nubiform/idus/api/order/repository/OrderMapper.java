package com.nubiform.idus.api.order.repository;

import com.nubiform.idus.api.order.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {
    public List<Order> getOrders();
}
