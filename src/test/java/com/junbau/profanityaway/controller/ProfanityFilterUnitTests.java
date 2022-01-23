package com.junbau.profanityaway.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junbau.profanityaway.enums.ReplacementMethod;
import com.junbau.profanityaway.model.ProfanityRequest;
import com.junbau.profanityaway.model.ProfanityResponse;
import com.junbau.profanityaway.service.ProfanityFilterServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProfanityController.class)
public class ProfanityFilterUnitTests {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProfanityFilterServiceImpl profanityFilterService;

    @Test
    public void whenFilterProfanity() throws Exception {
        String inputText = "Really normal text with no profanity";
        ProfanityRequest profanityRequest = new ProfanityRequest();
        profanityRequest.setText(inputText);
        profanityRequest.setAction(ReplacementMethod.CENSOR);

        ProfanityResponse profanityResponse = new ProfanityResponse(inputText.toLowerCase(), profanityRequest.getAction());

        when(profanityFilterService.profanityCheck(profanityRequest)).thenReturn(profanityResponse);

        mockMvc.perform(post("/filter/profanity")
                .content(objectMapper.writeValueAsString(profanityRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.replacementMethod")
                        .value(ReplacementMethod.CENSOR.toString()))
                .andExpect(jsonPath("$.filteredMessage")
                        .value(inputText.toLowerCase()));
    }

}
