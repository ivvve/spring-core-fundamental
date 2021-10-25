package com.example.member.service;

import com.example.member.domain.Grade;
import com.example.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class MemberServiceImplTest {
    private MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        // given
        final Member newMember = new Member(1L, "memberA", Grade.VIP);

        // when
        memberService.join(newMember);

        // then
        final Member foundMember = memberService.findMember(1L);
        assertThat(newMember).isEqualTo(foundMember);
    }
}
