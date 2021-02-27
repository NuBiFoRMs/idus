package com.nubiform.idus.api.member.service;

import com.nubiform.idus.api.member.model.Member;
import com.nubiform.idus.api.member.repository.MemberMapper;
import com.nubiform.idus.config.error.IdusException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberMapper memberMapper;

    public Member signIn(String memberId, String password) {
        Member member = memberMapper.getMember(memberId, password);

        if (member == null) {
            throw IdusException.of("invalid username or password");
        }

        return memberMapper.getMember(memberId, password);
    }

    public List<Member> getMembers() {
        List<Member> members = memberMapper.getMembers();

        if (members.size() == 0) {
            throw IdusException.of("No Data");
        }

        return memberMapper.getMembers();
    }

    public boolean signUp(Member member) {

        memberMapper.setMember(member);

        return true;
    }
}