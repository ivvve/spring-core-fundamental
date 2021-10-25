package com.example.member.service;

import com.example.member.domain.Member;

public interface MemberService {
    void join(Member member);

    Member findMember(Long memberId);
}
