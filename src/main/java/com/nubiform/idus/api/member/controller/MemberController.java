package com.nubiform.idus.api.member.controller;

import com.nubiform.idus.api.member.model.Member;
import com.nubiform.idus.api.member.service.MemberService;
import com.nubiform.idus.config.response.IdusErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/member")
@Tag(name = "Member", description = "멤버관련 api")
@SecurityRequirement(name = "Authorization")
@ApiResponse(responseCode = "200", description = "OK")
@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = IdusErrorResponse.class)))
@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = IdusErrorResponse.class)))
@ApiResponse(responseCode = "500", description = "Error Message", content = @Content(schema = @Schema(implementation = IdusErrorResponse.class)))
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member")
    @Operation(summary = "회원정보", description = "회원 정보조회를 수행합니다.",
            parameters = {@Parameter(name = "id", description = "회원아이디")})
    public Member getMember(String id) {
        return memberService.getMember(id);
    }

    @GetMapping("/members")
    @Operation(summary = "전체회원정보조회",
            description = "전체 회원 정보조회를 수행합니다.")
    public List<Member> getMembers() {
        return memberService.getMembers();
    }
}
