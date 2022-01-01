package com.example.profile;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.List;

public abstract class BaseProfileCondition implements Condition {
    @Override
    public boolean matches(final ConditionContext context, final AnnotatedTypeMetadata metadata) {
        String[] activeProfiles = context.getEnvironment().getActiveProfiles();
        final List<Profiles> profiles = getProfiles();

        for (String activeProfile : activeProfiles) {

            if (this.isActiveProfileContains(profiles, activeProfile)) {
                return true;
            }
        }

        return false;
    }

    abstract List<Profiles> getProfiles();

    private boolean isActiveProfileContains(List<Profiles> profiles, String activeProfile) {
        for (final Profiles profile : profiles) {

            if (profile.isEqualTo(activeProfile)) {
                return true;
            }
        }

        return false;
    }
}
