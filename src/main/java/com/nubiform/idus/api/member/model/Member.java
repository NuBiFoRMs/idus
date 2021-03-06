package com.nubiform.idus.api.member.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Member {
    private String memberId;
    private String memberName;
    private String nickName;
    private String password;
    private String phone;
    private String email;
    private String gender;
}
