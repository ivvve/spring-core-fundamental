package com.example.member.service;

import com.example.member.domain.Member;
import com.example.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

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
