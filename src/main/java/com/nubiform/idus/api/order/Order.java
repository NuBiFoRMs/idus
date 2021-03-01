package com.nubiform.idus.api.order;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Order {
    private String orderNumber;
    private String productName;
    private LocalDateTime paymentDate;
    private String memberId;
}