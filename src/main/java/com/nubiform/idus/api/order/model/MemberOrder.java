package com.nubiform.idus.api.order.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MemberOrder {
    private String memberId;
    private String memberName;
    private String nickName;
    private String phone;
    private String email;
    private String gender;
    private String orderNumber;
    private String productName;
    private LocalDateTime paymentDate;
}
