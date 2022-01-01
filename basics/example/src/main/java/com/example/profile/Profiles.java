package com.example.profile;

import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public enum Profiles {
    LOCAL("local"),
    DEVELOP("develop"),
    QA("qa"),
    STAGE("stage"),
    PROD("prod");

    private final String value;

    boolean isEqualTo(String profile) {
        return Objects.equals(this.value, profile);
    }
}
