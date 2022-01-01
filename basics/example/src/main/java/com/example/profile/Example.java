package com.example.profile;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(LocalDevelopProfileCondition.class)
public class Example {
}
