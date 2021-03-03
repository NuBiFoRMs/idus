package com.nubiform.idus.api.auth.controller;

import com.nubiform.idus.EncryptionUtils;
import com.nubiform.idus.JwtTokenProvider;
import com.nubiform.idus.api.member.model.Member;
import com.nubiform.idus.api.member.service.MemberService;
import com.nubiform.idus.config.error.IdusException;
import com.nubiform.idus.config.response.IdusErrorResponse;
import com.nubiform.idus.config.response.IdusResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        Member member;

        try {
            member = memberService.getMember(id);
        } catch (IdusException ex) {
            throw IdusException.of("invalid username or password");
        }

        if (!EncryptionUtils.encrypt(password).equals(member.getPassword()))
            throw IdusException.of("invalid username or password");

        List<String> roles = new ArrayList<>();
        roles.add("USER");

        String token = jwtTokenProvider.createToken(member.getMemberId(), roles);

        log.debug("memberId : {}", member.getMemberId());
        log.debug("token : {}", token);

        return token;
    }

    @PostMapping("/sign-out")
    @Operation(summary = "로그아웃", description = "회원 로그아웃을 수행합니다.")
    public IdusResponse signOut() {
        if (memberService.signOut())
            return new IdusResponse();
        else
            return new IdusErrorResponse();
    }
}
