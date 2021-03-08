package com.nubiform.idus.api.auth.service;

import com.nubiform.idus.api.auth.model.Auth;
import com.nubiform.idus.api.auth.model.Sign;
import com.nubiform.idus.api.auth.repository.AuthMapper;
import com.nubiform.idus.api.member.repository.MemberMapper;
import com.nubiform.idus.config.error.IdusException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class AuthServiceTest {

    private AuthService authService;

    @MockBean
    private MemberMapper memberMapper;

    @MockBean
    private AuthMapper authMapper;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void before() {
        this.authService = new AuthService(memberMapper, authMapper, passwordEncoder);
    }

    @Test
    @DisplayName("회원 로그인(인증)")
    void signIn() {
        Auth auth = new Auth();
        auth.setMemberId("user");
        auth.setPassword("encodedPassword");

        when(authMapper.getAuth("user")).thenReturn(auth);
        when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(true);

        Sign sign = new Sign();
        sign.setId("user");
        sign.setPassword("password");

        Auth result = authService.signIn(sign);

        assertEquals("user", result.getMemberId());
        assertEquals("encodedPassword", result.getPassword());

        verify(authMapper, atLeastOnce()).getAuth("user");
        verify(passwordEncoder, atLeastOnce()).matches("password", "encodedPassword");
    }

    @Test
    @DisplayName("회원 로그인(인증) 예외상황 memberId 불일치")
    void signInException01() {
        Auth auth = new Auth();
        auth.setMemberId("user");
        auth.setPassword("encodedPassword");

        when(authMapper.getAuth("user")).thenReturn(null);
        when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(true);

        Sign sign = new Sign();
        sign.setId("user");
        sign.setPassword("password");

        IdusException exception = assertThrows(IdusException.class, () -> authService.signIn(sign));

        assertEquals(500, exception.getStatus());
        verify(authMapper, atLeastOnce()).getAuth("user");
    }

    @Test
    @DisplayName("회원 로그인(인증) 예외상황 password 불일치")
    void signInException02() {
        Auth auth = new Auth();
        auth.setMemberId("user");
        auth.setPassword("encodedPassword");

        when(authMapper.getAuth("user")).thenReturn(auth);
        when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(false);

        Sign sign = new Sign();
        sign.setId("user");
        sign.setPassword("password");

        IdusException exception = assertThrows(IdusException.class, () -> authService.signIn(sign));

        assertEquals(500, exception.getStatus());
        verify(passwordEncoder, atLeastOnce()).matches("password", "encodedPassword");
    }

    @Test
    @DisplayName("회원 로그인(인증) 예외상황 memberId, password 불일치")
    void signInException03() {
        Auth auth = new Auth();
        auth.setMemberId("user");
        auth.setPassword("encodedPassword");

        when(authMapper.getAuth("user")).thenReturn(null);
        when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(false);

        Sign sign = new Sign();
        sign.setId("user");
        sign.setPassword("password");

        IdusException exception = assertThrows(IdusException.class, () -> authService.signIn(sign));

        assertEquals(500, exception.getStatus());
        verify(authMapper, atLeastOnce()).getAuth("user");
    }

    @Test
    @DisplayName("회원 가입")
    void signUp() {

    }

    @Test
    @DisplayName("회원 로그아웃")
    void signOut() {

    }
}
