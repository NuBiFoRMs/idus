package com.nubiform.idus.api.member.controller;

import com.nubiform.idus.IdusResponse;
import com.nubiform.idus.api.member.model.Member;
import com.nubiform.idus.api.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/member")
@Tag(name = "Member", description = "멤버관련 블라블라")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/sign-in")
    @Operation(summary = "로그인", description = "회원 로그인을 수행합니다.", parameters = {@Parameter(name = "id", description = "회원아이디")})
    public String signIn(String id) {
        return "OK";
    }

    @GetMapping("/members")
    public IdusResponse<List<Member>> getMembers() {
        return IdusResponse.success(memberService.getMembers());
    }
}