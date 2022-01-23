package com.junbau.profanityaway.model;


import com.junbau.profanityaway.enums.ReplacementMethod;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfanityResponse {

    private String filteredMessage;
    private ReplacementMethod replacementMethod;

}
