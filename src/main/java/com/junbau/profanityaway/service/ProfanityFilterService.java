package com.junbau.profanityaway.service;

import com.junbau.profanityaway.model.ProfanityRequest;
import com.junbau.profanityaway.model.ProfanityResponse;


public interface ProfanityFilterService {

    public ProfanityResponse profanityCheck(ProfanityRequest profanityResponse);

}
