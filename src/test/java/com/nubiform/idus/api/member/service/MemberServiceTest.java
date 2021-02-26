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
    @DisplayName("테스트 명")
    void getMember() {

        when(memberMapper.getMembers()).thenReturn(new ArrayList<>());

        List<Member> members = memberService.getMembers();

        assertEquals(0, members.size());
    }

    @Test
    @DisplayName("exception check")
    void exception() {

        IdusException exception = assertThrows(IdusException.class, () -> memberService.getMembers());
        assertEquals(500, exception.getStatus());

//        verify(memberMapper, times(1)).getMembers();
        verify(memberMapper, never()).getMembers();
    }
}