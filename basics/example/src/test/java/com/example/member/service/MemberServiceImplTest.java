package com.example.member.service;

import com.example.AppConfig;
import com.example.member.domain.Grade;
import com.example.member.domain.Member;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberServiceImplTest {
    final AppConfig appConfig = new AppConfig();
    private MemberService memberService = appConfig.memberService();

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
