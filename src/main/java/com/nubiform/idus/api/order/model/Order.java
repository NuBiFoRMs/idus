package com.nubiform.idus.api.order.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Order {
    private String orderNumber;
    private String productName;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime paymentDate;
    private String memberId;
}
