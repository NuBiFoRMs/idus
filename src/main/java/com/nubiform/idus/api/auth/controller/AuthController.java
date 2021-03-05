package com.nubiform.idus.api.auth.controller;

import com.nubiform.idus.api.auth.AuthService;
import com.nubiform.idus.api.auth.model.Auth;
import com.nubiform.idus.api.member.model.Member;
import com.nubiform.idus.config.error.IdusException;
import com.nubiform.idus.config.response.IdusErrorResponse;
import com.nubiform.idus.config.response.IdusResponse;
import com.nubiform.idus.config.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authorization", description = "인증관련 api")
@ApiResponse(responseCode = "500", description = "Error Message", content = @Content(schema = @Schema(implementation = IdusErrorResponse.class)))
public class AuthController {

    private final AuthService authService;

    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("sign-in")
    @Operation(summary = "로그인", description = "회원 로그인을 수행합니다.",
            responses = {@ApiResponse(responseCode = "200", description = "OK")})
    public String signIn(@RequestBody Auth auth) {
        Member member = authService.signIn(auth.getId(), auth.getPassword());

        List<String> roles = new ArrayList<>();
        roles.add("USER");

        String token = jwtTokenProvider.createToken(member.getMemberId(), roles);

        log.debug("memberId : {}", member.getMemberId());
        log.debug("token : {}", token);

        return token;
    }

    @PostMapping("/sign-up")
    @Operation(summary = "회원가입", description = "회원 가입을 수행합니다.",
            responses = {@ApiResponse(responseCode = "200", description = "OK")})
    public IdusResponse signUp(@RequestBody Member member) {
        if (authService.signUp(member))
            return new IdusResponse();
        else
            return new IdusErrorResponse();
    }

    @PostMapping("/sign-out")
    @Operation(summary = "로그아웃", description = "회원 로그아웃을 수행합니다.",
            parameters = {@Parameter(name = "X-AUTH-TOKEN", description = "JWT Token", in = ParameterIn.HEADER)},
            responses = {@ApiResponse(responseCode = "200", description = "OK")})
    public IdusResponse signOut() {
        String token = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();

        log.debug(SecurityContextHolder.getContext().getAuthentication().toString());
        log.debug("token : {}", token);

        if (token == null || "".equals(token))
            throw IdusException.of("there is no authentication");

        if (authService.signOut(token))
            return new IdusResponse();
        else
            return new IdusErrorResponse();
    }
}
