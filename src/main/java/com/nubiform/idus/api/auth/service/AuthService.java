package com.nubiform.idus.api.auth.service;

import com.nubiform.idus.api.auth.model.Auth;
import com.nubiform.idus.api.auth.model.Sign;
import com.nubiform.idus.api.auth.repository.AuthMapper;
import com.nubiform.idus.api.member.model.Member;
import com.nubiform.idus.api.member.repository.MemberMapper;
import com.nubiform.idus.config.error.IdusException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final MemberMapper memberMapper;

    private final AuthMapper authMapper;

    private final PasswordEncoder passwordEncoder;
    private final StringRedisTemplate redisTemplate;

    public Auth signIn(Sign sign) {
        Auth auth;

        try {
            auth = getAuth(sign.getId());
        } catch (Exception ex) {
            throw IdusException.of("invalid username or password");
        }

        if (auth == null || !passwordEncoder.matches(sign.getPassword(), auth.getPassword()))
            throw IdusException.of("invalid username or password");

        log.debug("encoded : {}", passwordEncoder.encode(sign.getPassword()));
        log.debug("member.getPassword() : {}", auth.getPassword());

        return auth;
    }

    @Transactional(readOnly = true)
    public Auth getAuth(String memberId) {
        Auth auth = authMapper.getAuth(memberId);

        if (auth == null) {
            throw IdusException.of("no data");
        }

        return auth;
    }

    @Transactional
    public boolean signUp(Member member) {
        // memberId validation
        String memberIdRegex = "^[a-zA-Z]{8,}$";
        if (!member.getMemberId().matches(memberIdRegex))
            throw IdusException.of("invalid user id (영문 대소문자만 허용, 최소 8자 이상)");

        // memberName validation
        String memberNameRegex = "^[a-zA-Z가-힣]+$";
        if (!member.getMemberName().matches(memberNameRegex))
            throw IdusException.of("invalid user name (한글, 영문 대소문자만 허용)");

        // nickName validation
        String nickNameRegex = "^[a-z]+$";
        if (!member.getNickName().matches(nickNameRegex))
            throw IdusException.of("invalid nickname (영문 소문자만 허용)");

        // password validation
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{10,}";
        if (!member.getPassword().matches(passwordRegex))
            throw IdusException.of("invalid password (영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함, 최소 10자 이상)");

        // phone validation
        String phoneRegex = "^[0-9]+$";
        if (!member.getPhone().matches(phoneRegex))
            throw IdusException.of("invalid phone number (숫자)");

        // email validation
        String emailRegex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        if (!member.getEmail().matches(emailRegex))
            throw IdusException.of("invalid email address (이메일 형식)");

        // duplicate validation
        if (memberMapper.getMember(member.getMemberId()) != null)
            throw IdusException.of("duplicate user account (중복된 회원가입)");

        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberMapper.setMember(member);

        return true;
    }

    @Transactional
    public boolean signOut(String token) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(token, token);
        return true;
    }
}
