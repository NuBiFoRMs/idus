package com.nubiform.idus.api.auth.controller;

import com.nubiform.idus.api.member.model.Member;
import com.nubiform.idus.api.member.service.MemberService;
import com.nubiform.idus.config.error.IdusException;
import com.nubiform.idus.config.response.IdusErrorResponse;
import com.nubiform.idus.config.response.IdusResponse;
import com.nubiform.idus.config.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authorization", description = "인증관련 api")
public class AuthController {

    private final MemberService memberService;

    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("sign-in")
    @Operation(summary = "로그인", description = "회원 로그인을 수행합니다.", parameters = {@Parameter(name = "id", description = "회원아이디"), @Parameter(name = "password", description = "회원비밀번호")})
    public String signIn(@RequestParam String id, @RequestParam String password) {
        Member member = memberService.signIn(id, password);

        List<String> roles = new ArrayList<>();
        roles.add("USER");

        String token = jwtTokenProvider.createToken(member.getMemberId(), roles);

        log.debug("memberId : {}", member.getMemberId());
        log.debug("token : {}", token);

        return token;
    }

    @PostMapping("/sign-up")
    @Operation(summary = "회원가입", description = "회원 가입을 수행합니다.", parameters = {@Parameter(name = "member", description = "회원정보")})
    public IdusResponse signUp(@RequestBody Member member) {
        if (memberService.signUp(member))
            return new IdusResponse();
        else
            return new IdusErrorResponse();
    }

    @PostMapping("/sign-out")
    @Operation(summary = "로그아웃", description = "회원 로그아웃을 수행합니다.", parameters = {@Parameter(name = "X-AUTH-TOKEN", description = "JWT Token", in = ParameterIn.HEADER)})
    public IdusResponse signOut() {
        String token = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();

        log.debug(SecurityContextHolder.getContext().getAuthentication().toString());
        log.debug("token : {}", token);

        if (token == null || "".equals(token))
            throw IdusException.of("there is no authentication");

        if (memberService.signOut(token))
            return new IdusResponse();
        else
            return new IdusErrorResponse();
    }
}
