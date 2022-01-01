package com.example.profile;

import java.util.List;

public class LocalProfileCondition extends BaseProfileCondition {
    @Override
    List<Profiles> getProfiles() {
        return List.of(Profiles.LOCAL);
    }
}
