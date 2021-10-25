package com.example.member.service;

import com.example.member.domain.Member;
import com.example.member.repository.MemberRepository;
import com.example.member.repository.MemoryMemberRepository;

public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(final Member member) {
        this.memberRepository.save(member);
    }

    @Override
    public Member findMember(final Long memberId) {
        return this.memberRepository.findById(memberId)
                .orElseThrow();
    }
}
