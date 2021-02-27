package com.nubiform.idus.api.member.repository;

import com.nubiform.idus.api.member.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MemberMapper {
    public Member getMember(String memberId, String password);
    public List<Member> getMembers();
    public void setMember(Member member);
}
