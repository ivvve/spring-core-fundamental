package com.example.member.repository;

import com.example.member.domain.Member;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class MemoryMemberRepository implements MemberRepository {
    private static final Map<Long, Member> members = new HashMap<>();

    @Override
    public void save(Member member) {
        members.put(member.getId(), member);
    }

    @Override
    public Optional<Member> findById(final Long memberId) {
        return Optional.ofNullable(members.get(memberId));
    }
}
