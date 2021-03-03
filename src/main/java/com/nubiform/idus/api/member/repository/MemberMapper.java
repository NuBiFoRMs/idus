package com.nubiform.idus.api.member.repository;

import com.nubiform.idus.api.member.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MemberMapper {
    Member signIn(String memberId, String password);
    void setMember(Member member);
    Member getMember(String memberId);
    List<Member> getMembers();
}
