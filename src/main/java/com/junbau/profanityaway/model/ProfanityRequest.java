package com.junbau.profanityaway.model;

import com.junbau.profanityaway.enums.ReplacementMethod;
import com.junbau.profanityaway.validator.ReplacementMethodSubset;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfanityRequest {

    @NotNull(message = "Text to censor is required")
    @NotEmpty(message = "Please provide some text")
    private String text;

    @NotNull(message = "Action must be one of CENSOR, REPLACE_WITH_FACT or REPLACE_WITH_WORD")
    @ReplacementMethodSubset(anyOf = {ReplacementMethod.CENSOR, ReplacementMethod.REPLACE_WITH_WORD, ReplacementMethod.REPLACE_WITH_FACT})
    private ReplacementMethod action;

    private String replacementWord;

}
