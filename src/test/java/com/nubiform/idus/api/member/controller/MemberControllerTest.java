package com.nubiform.idus.api.member.controller;

import com.nubiform.idus.AbstractControllerTest;
import com.nubiform.idus.api.auth.model.Auth;
import com.nubiform.idus.api.auth.service.AuthService;
import com.nubiform.idus.api.member.model.Member;
import com.nubiform.idus.api.member.service.MemberService;
import com.nubiform.idus.config.security.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MemberControllerTest extends AbstractControllerTest {

    @Autowired
    private MemberController memberController;

    @MockBean
    private AuthService authService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private MemberService memberService;

    @Override
    protected Object controller() {
        return memberController;
    }

    @BeforeEach
    void before() {
        Member member = new Member();
        member.setMemberId("user");
        when(memberService.getMember("user")).thenReturn(member);
        member = new Member();
        member.setMemberId("admin");
        when(memberService.getMember("admin")).thenReturn(member);
        member = new Member();
        member.setMemberId("useradmin");
        when(memberService.getMember("useradmin")).thenReturn(member);

        Auth auth = new Auth();
        auth.setMemberId("user");
        auth.setRoles("ROLE_USER");
        when(authService.getAuth("user")).thenReturn(auth);
        auth = new Auth();
        auth.setMemberId("admin");
        auth.setRoles("ROLE_ADMIN");
        when(authService.getAuth("admin")).thenReturn(auth);
        auth = new Auth();
        auth.setMemberId("useradmin");
        auth.setRoles("ROLE_USER,ROLE_ADMIN");
        when(authService.getAuth("useradmin")).thenReturn(auth);
    }

    @Test
    @DisplayName("URI: /api/v1/member/member")
    void getMember() throws Exception {
        String token = jwtTokenProvider.createToken("user");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "user");
        mockMvc.perform(get("/api/v1/member/member")
                .header("Authorization", "Bearer " + token)
                .params(params))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberId").value("user"));

        verify(memberService, times(1)).getMember("user");
    }

    @Test
    @DisplayName("URI: /api/v1/member/member : Unauthorized")
    void getMemberUnauthorized() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "user");
        mockMvc.perform(get("/api/v1/member/member")
                .params(params))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("URI: /api/v1/member/members")
    void getMembers() throws Exception {
        List<Member> memberList = new ArrayList<>();
        Member member = new Member();
        member.setMemberId("user");
        memberList.add(member);
        when(memberService.getMembers()).thenReturn(memberList);

        String token = jwtTokenProvider.createToken("admin");

        mockMvc.perform(get("/api/v1/member/members")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].memberId").value("user"));

        verify(memberService, times(1)).getMembers();
    }

    @Test
    @DisplayName("URI: /api/v1/member/members : ROLE_USER ROLE_ADMIN")
    void getMembersUserAdmin() throws Exception {
        List<Member> memberList = new ArrayList<>();
        Member member = new Member();
        member.setMemberId("user");
        memberList.add(member);
        when(memberService.getMembers()).thenReturn(memberList);

        String token = jwtTokenProvider.createToken("useradmin");

        mockMvc.perform(get("/api/v1/member/members")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].memberId").value("user"));

        verify(memberService, times(1)).getMembers();
    }

    @Test
    @DisplayName("URI: /api/v1/member/members : Unauthorized")
    void getMembersUnauthorized() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "user");
        mockMvc.perform(get("/api/v1/member/members")
                .params(params))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("URI: /api/v1/member/members : Forbidden")
    void getMemberForbidden() throws Exception {
        String token = jwtTokenProvider.createToken("user");

        mockMvc.perform(get("/api/v1/member/members")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isForbidden());
    }
}
