package com.nubiform.idus.api.auth.service;

import com.nubiform.idus.api.auth.repository.AuthMapper;
import com.nubiform.idus.api.member.repository.MemberMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class AuthServiceTest {

    private AuthService authService;

    @MockBean
    private MemberMapper memberMapper;

    @MockBean
    private AuthMapper authMapper;

    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private StringRedisTemplate redisTemplate;

    @BeforeEach
    void before() {
        this.authService = new AuthService(memberMapper, authMapper, passwordEncoder,redisTemplate);
    }

    @Test
    void signIn() {

    }

    @Test
    void getAuth() {

    }

    @Test
    void signUp() {

    }

    @Test
    void signOut() {

    }
}
