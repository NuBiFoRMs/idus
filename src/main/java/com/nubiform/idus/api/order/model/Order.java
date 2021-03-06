package com.nubiform.idus.api.order.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Order {
    private String orderNumber;
    private String productName;
    private LocalDateTime paymentDate;
    private String memberId;
}
