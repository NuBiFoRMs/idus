package com.nubiform.idus.api.member.service;

import com.nubiform.idus.api.member.model.Member;
import com.nubiform.idus.api.member.repository.MemberMapper;
import com.nubiform.idus.config.error.IdusException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class MemberServiceTest {

    private MemberService memberService;

    @MockBean
    private MemberMapper memberMapper;

    @BeforeEach
    void before() {
        this.memberService = new MemberService(memberMapper);
    }

    @Test
    @DisplayName("단일 회원 상세 정보 조회")
    void getMember() {
        Member someMember01 = new Member();
        someMember01.setMemberId("user01");
        Member someMember02 = new Member();
        someMember02.setMemberId("user02");

        when(memberMapper.getMember("user01")).thenReturn(someMember01);
        when(memberMapper.getMember("user02")).thenReturn(someMember02);

        assertEquals("user01", memberService.getMember("user01").getMemberId());
        assertEquals("user02", memberService.getMember("user02").getMemberId());

        verify(memberMapper).getMember("user01");
        verify(memberMapper).getMember("user02");
    }

    @Test
    @DisplayName("단일 회원 상세 정보 조회 예외상황")
    void getMemberException() {
        when(memberMapper.getMember("user")).thenReturn(null);

        IdusException exception = assertThrows(IdusException.class, () -> memberService.getMember("user"));

        assertEquals(500, exception.getStatus());
        verify(memberMapper, times(1)).getMember("user");
    }

    @Test
    @DisplayName("전체 회원 정보 조회")
    void getMembers() {
        List<Member> memberList = new ArrayList<>();
        Member member = new Member();
        member.setMemberId("user01");
        memberList.add(member);
        member = new Member();
        member.setMemberId("user02");
        memberList.add(member);

        when(memberMapper.getMembers()).thenReturn(memberList);

        List<Member> members = memberService.getMembers();

        assertEquals(2, members.size());
    }

    @Test
    @DisplayName("전체 회원 정보 조회 예외상황")
    void getMembersException() {
        when(memberMapper.getMembers()).thenReturn(new ArrayList<>());

        IdusException exception = assertThrows(IdusException.class, () -> memberService.getMembers());

        verify(memberMapper, times(1)).getMembers();
    }
}
