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

    public List<Member> getMembers() {
        if (true) {
            throw IdusException.of("ddsdsfsdfd");
        }
        return memberMapper.getMembers();
    }
}