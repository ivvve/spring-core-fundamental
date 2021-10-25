package com.example.member.repository;

import com.example.member.domain.Member;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemoryMemberRepository implements MemberRepository {
    private final Map<Long, Member> members = new HashMap<>();

    @Override
    public void save(Member member) {
        this.members.put(member.getId(), member);
    }

    @Override
    public Optional<Member> findById(final Long memberId) {
        return Optional.ofNullable(this.members.get(memberId));
    }
}
