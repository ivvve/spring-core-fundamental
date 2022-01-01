package com.example.profile;

import java.util.List;

public class LocalDevelopProfileCondition extends BaseProfileCondition {
    @Override
    List<Profiles> getProfiles() {
        return List.of(Profiles.LOCAL, Profiles.DEVELOP);
    }
}
