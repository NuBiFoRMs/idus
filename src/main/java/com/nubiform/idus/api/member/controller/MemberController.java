package com.nubiform.idus.api.member.controller;

import com.nubiform.idus.api.member.model.Member;
import com.nubiform.idus.api.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/member")
@Tag(name = "Member", description = "멤버관련 api")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member")
    @Operation(summary = "회원정보", description = "회원 정보조회를 수행합니다.", parameters = {@Parameter(name = "id", description = "회원아이디")})
    public Member getMember(String id) {
        return memberService.getMember(id);
    }

    @GetMapping("/members")
    @Operation(summary = "전체회원정보조회", description = "전체 회원 정보조회를 수행합니다.")
    public List<Member> getMembers() {
        return memberService.getMembers();
    }
}
