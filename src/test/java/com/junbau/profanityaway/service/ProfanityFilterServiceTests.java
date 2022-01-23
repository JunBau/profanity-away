package com.junbau.profanityaway.service;

import com.junbau.profanityaway.enums.ReplacementMethod;
import com.junbau.profanityaway.model.ProfanityRequest;
import com.junbau.profanityaway.model.ProfanityResponse;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ProfanityFilterServiceTests {

    ProfanityFilterServiceImpl profanityFilterService = new ProfanityFilterServiceImpl();

    @BeforeEach
    void setUp() {
        ProfanityFilterServiceImpl profanityFilterService = new ProfanityFilterServiceImpl();
    }

    @Test
    @DisplayName("Should return same text to lowercase with no profanity 'CENSOR' replacement method")
    public void testWithNoProfanity() {
        String inputText = "Really normal text with no profanity";
        ProfanityRequest profanityRequest = new ProfanityRequest();
        profanityRequest.setText(inputText);
        profanityRequest.setAction(ReplacementMethod.CENSOR);

        ProfanityResponse profanityResponse = profanityFilterService.profanityCheck(profanityRequest);

        assertThat(profanityResponse.getFilteredMessage(), equalTo(inputText.toLowerCase()));
        assertThat(profanityResponse.getReplacementMethod(), equalTo(ReplacementMethod.CENSOR));
    }

    @Test
    @DisplayName("Should return the same text censored with 'CENSOR' replacement method")
    public void when_Censor_ProfanityShouldBeReplaced() {
        String inputText = "The profanity text is: xrated";
        String expectedText = "the profanity text is: ******";
        ProfanityRequest profanityRequest = new ProfanityRequest();
        profanityRequest.setText(inputText);
        profanityRequest.setAction(ReplacementMethod.CENSOR);

        ProfanityResponse profanityResponse = profanityFilterService.profanityCheck(profanityRequest);

        assertThat(profanityResponse.getFilteredMessage(), equalTo(expectedText));
        assertThat(profanityResponse.getReplacementMethod(), equalTo(ReplacementMethod.CENSOR));
    }

    @Test
    @DisplayName("Should return a random fact with 'REPLACE_WITH_FACT' replacement method")
    public void when_ReplaceWithFact_AllTextShouldBeReplaced() {
        String inputText = "The profanity text is: xrated";
        String expectedText = "the profanity text is:";
        ProfanityRequest profanityRequest = new ProfanityRequest();
        profanityRequest.setText(inputText);
        profanityRequest.setAction(ReplacementMethod.REPLACE_WITH_FACT);

        ProfanityResponse profanityResponse = profanityFilterService.profanityCheck(profanityRequest);

        assertThat(profanityResponse.getFilteredMessage(), not(containsString(expectedText)));
        assertThat(profanityResponse.getReplacementMethod(), equalTo(ReplacementMethod.REPLACE_WITH_FACT));
    }

    @Test
    @DisplayName("Should return replace word with 'REPLACE_WITH_WORD' replacement method")
    public void when_ReplaceWithWord_ProfanityShouldBeReplaced() {
        String inputText = "The profanity text is: xrated";
        String replacementWord = "replaced";
        String expectedText = "the profanity text is: " + replacementWord;
        ProfanityRequest profanityRequest = new ProfanityRequest();
        profanityRequest.setText(inputText);
        profanityRequest.setAction(ReplacementMethod.REPLACE_WITH_WORD);
        profanityRequest.setReplacementWord(replacementWord);

        ProfanityResponse profanityResponse = profanityFilterService.profanityCheck(profanityRequest);

        assertThat(profanityResponse.getFilteredMessage(), equalTo(expectedText));
        assertThat(profanityResponse.getReplacementMethod(), equalTo(ReplacementMethod.REPLACE_WITH_WORD));
    }

    @Test
    @DisplayName("Should censor with 'REPLACE_WITH_WORD' replacement method when no replacement provided")
    public void when_ReplaceWithWord_AndNoReplacement_ProfanityShouldBeCensored() {
        String inputText = "The profanity text is: xrated";
        String expectedText = "the profanity text is: ******";
        ProfanityRequest profanityRequest = new ProfanityRequest();
        profanityRequest.setText(inputText);
        profanityRequest.setAction(ReplacementMethod.REPLACE_WITH_WORD);

        ProfanityResponse profanityResponse = profanityFilterService.profanityCheck(profanityRequest);

        assertThat(profanityResponse.getFilteredMessage(), equalTo(expectedText));
        assertThat(profanityResponse.getReplacementMethod(), equalTo(ReplacementMethod.REPLACE_WITH_WORD));
    }

}
