package com.nubiform.idus.api.member.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
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