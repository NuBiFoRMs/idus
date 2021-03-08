package com.nubiform.idus.api.auth.controller;

import com.nubiform.idus.api.auth.model.Auth;
import com.nubiform.idus.api.auth.model.Sign;
import com.nubiform.idus.api.auth.service.AuthService;
import com.nubiform.idus.api.member.model.Member;
import com.nubiform.idus.config.error.IdusException;
import com.nubiform.idus.config.response.IdusErrorResponse;
import com.nubiform.idus.config.response.IdusResponse;
import com.nubiform.idus.config.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
@OpenAPIDefinition(info = @Info(title = "Idus Project", version = "v1", description = "Idus Project"))
@Tag(name = "Authorization", description = "인증관련 api")
@SecurityScheme(type = SecuritySchemeType.HTTP, scheme = "Bearer", bearerFormat = "JWT", name = "Authorization", in = SecuritySchemeIn.HEADER)
@ApiResponse(responseCode = "200", description = "OK")
@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = IdusErrorResponse.class)))
@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = IdusErrorResponse.class)))
@ApiResponse(responseCode = "500", description = "Error Message", content = @Content(schema = @Schema(implementation = IdusErrorResponse.class)))
public class AuthController {

    private final AuthService authService;

    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("sign-in")
    @Operation(summary = "로그인", description = "회원 로그인을 수행합니다.")
    public String signIn(@RequestBody Sign sign) {
        Auth auth = authService.signIn(sign);

        String token = jwtTokenProvider.createToken(auth.getMemberId());

        log.debug("memberId : {}", auth.getMemberId());
        log.debug("token : {}", token);

        return token;
    }

    @PostMapping("/sign-up")
    @Operation(summary = "회원가입", description = "회원 가입을 수행합니다.")
    public IdusResponse signUp(@RequestBody Member member) {
        if (authService.signUp(member))
            return new IdusResponse();
        else
            return new IdusErrorResponse();
    }

    @PostMapping("/sign-out")
    @Operation(summary = "로그아웃", description = "회원 로그아웃을 수행합니다.",
            security = @SecurityRequirement(name = "Authorization"))
    public IdusResponse signOut(@Parameter(hidden = true) @AuthenticationPrincipal Auth auth) {
        try {
            String token = auth.getToken();

            log.debug(SecurityContextHolder.getContext().getAuthentication().toString());
            log.debug("token : {}", token);

            if (token == null || "".equals(token))
                throw IdusException.of("there is no authentication");

            if (authService.signOut(token))
                return new IdusResponse();
            else
                return new IdusErrorResponse();
        } catch (Exception ex) {
            throw IdusException.of("there is no authentication");
        }
    }
}
