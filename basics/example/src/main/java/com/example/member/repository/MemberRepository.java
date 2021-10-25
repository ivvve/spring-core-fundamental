package com.example.member.repository;

import com.example.member.domain.Member;

import java.util.Optional;

public interface MemberRepository {
    void save(Member member);

    Optional<Member> findById(Long memberId);
}
