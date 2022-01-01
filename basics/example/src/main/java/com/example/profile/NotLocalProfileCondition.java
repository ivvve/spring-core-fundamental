package com.example.profile;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class NotLocalProfileCondition implements Condition {
    private final Condition condition = new LocalProfileCondition();

    @Override
    public boolean matches(final ConditionContext context, final AnnotatedTypeMetadata metadata) {
        return !this.condition.matches(context, metadata);
    }
}
