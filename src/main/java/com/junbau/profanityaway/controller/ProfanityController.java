package com.junbau.profanityaway.controller;

import com.junbau.profanityaway.service.ProfanityFilterService;
import com.junbau.profanityaway.model.ProfanityRequest;
import com.junbau.profanityaway.model.ProfanityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/filter")
public class ProfanityController {

    @Autowired ProfanityFilterService profanityFilterService;

    @ResponseBody
    @PostMapping(
            value = "/profanity",
            consumes = MimeTypeUtils.APPLICATION_JSON_VALUE,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfanityResponse> filterProfanity(@RequestBody @Valid ProfanityRequest profanityRequest,
                                                             BindingResult bindingResult) {
        ProfanityResponse profanityResponse = profanityFilterService.profanityCheck(profanityRequest);
        return ResponseEntity.ok(profanityResponse);
    }

}
