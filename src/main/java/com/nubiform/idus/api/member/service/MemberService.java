package com.nubiform.idus.api.member.service;

import com.nubiform.idus.EncryptionUtils;
import com.nubiform.idus.api.member.model.Member;
import com.nubiform.idus.api.member.repository.MemberMapper;
import com.nubiform.idus.config.error.IdusException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberMapper memberMapper;

    public Member signIn(String memberId, String password) {
        Member member = memberMapper.signIn(memberId, EncryptionUtils.encrypt(password));

        if (member == null) {
            throw IdusException.of("invalid username or password");
        }

        return member;
    }

    public boolean signUp(Member member) {
        // nickName validation
        String nickNameRegex = "^[a-z]+$";
        if (!member.getNickName().matches(nickNameRegex))
            throw IdusException.of("invalid nickname");

        // phone validation
        String phoneRegex = "^[0-9]+$";
        if (!member.getPhone().matches(phoneRegex))
            throw IdusException.of("invalid phone number");

        // email validation
        String emailRegex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        if (!member.getEmail().matches(emailRegex))
            throw IdusException.of("invalid email address");

        // duplicate validation
        if (memberMapper.getMember(member.getMemberId()) != null)
            throw IdusException.of("duplicate user account");

        member.setPassword(EncryptionUtils.encrypt(member.getPassword()));
        memberMapper.setMember(member);

        return true;
    }

    public boolean signOut() {
        return true;
    }

    public Member getMember(String memberId) {
        Member member = memberMapper.getMember(memberId);

        if (member == null) {
            throw IdusException.of("no data");
        }

        return member;
    }

    public List<Member> getMembers() {
        List<Member> members = memberMapper.getMembers();

        if (members.size() == 0) {
            throw IdusException.of("no data");
        }

        return members;
    }
}