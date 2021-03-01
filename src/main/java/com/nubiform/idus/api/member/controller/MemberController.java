package com.nubiform.idus.api.member.controller;

import com.nubiform.idus.IdusResponse;
import com.nubiform.idus.api.member.model.Member;
import com.nubiform.idus.api.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/member")
@Tag(name = "Member", description = "멤버관련 api")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/sign-in")
    @Operation(summary = "로그인", description = "회원 로그인을 수행합니다.", parameters = {@Parameter(name = "id", description = "회원아이디"), @Parameter(name = "password", description = "회원비밀번호")})
    public Member signIn(String id, String password) {
        return memberService.signIn(id, password);
    }

    @PostMapping("/sign-up")
    @Operation(summary = "회원가입", description = "회원 가입을 수행합니다.", parameters = {@Parameter(name = "member", description = "회원정보")})
    public boolean signUp(@RequestBody Member member) {
        return memberService.signUp(member);
    }

    @GetMapping("/sign-out")
    @Operation(summary = "로그아웃", description = "회원 로그아웃을 수행합니다.")
    public boolean signOut() {
        return memberService.signOut();
    }

    @GetMapping("/members")
    public IdusResponse getMembers() {
        return IdusResponse.success(memberService.getMembers());
    }
}