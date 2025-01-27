package com.ltp.gradesubmission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.RetentionPolicy;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ScoreValidator.class)
public @interface Score {
    
    String message() default "Invalid score";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
