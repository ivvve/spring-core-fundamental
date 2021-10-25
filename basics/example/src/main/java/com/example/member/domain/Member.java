package com.example.member.domain;

import java.util.Objects;

public class Member {
    private Long id;
    private String name;
    private Grade grade;

    public Member(final Long id, final String name, final Grade grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public boolean isVip() {
        return Objects.equals(this.grade, Grade.VIP);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Grade getGrade() {
        return grade;
    }
}
