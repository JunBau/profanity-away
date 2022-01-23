package com.junbau.profanityaway.validator;

import com.junbau.profanityaway.enums.ReplacementMethod;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class ReplacementMethodSubsetValidator implements ConstraintValidator<ReplacementMethodSubset, ReplacementMethod> {
    private ReplacementMethod[] subset;

    @Override
    public void initialize(ReplacementMethodSubset constraintAnnotation) {
        this.subset = constraintAnnotation.anyOf();
    }

    @Override
    public boolean isValid(ReplacementMethod replacementMethod, ConstraintValidatorContext constraintValidatorContext) {
        return replacementMethod == null || Arrays.asList(subset).contains(replacementMethod);
    }
}
