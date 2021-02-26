package com.nubiform.idus.api.member.model;

import lombok.Data;

@Data
public class Member {
    private String memberId;
    private String memberName;
    private String nickName;
    private String password;
    private String phone;
    private String email;
    private String gender;
}