package com.junbau.profanityaway.service;

import com.junbau.profanityaway.model.ProfanityRequest;
import com.junbau.profanityaway.model.ProfanityResponse;
import com.junbau.profanityaway.utils.FileReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ProfanityFilterServiceImpl implements ProfanityFilterService {

    private ArrayList<String> badWords;
    private ArrayList<String> randomFacts;
    private Logger logger = LogManager.getLogger(ProfanityFilterServiceImpl.class);


    public ProfanityFilterServiceImpl() {
        this.badWords = new FileReader(new File("src/main/resources/profanity-list.txt"))
                .getTextList()
                .stream()
                .sorted((s1, s2) -> s2.length() - s1.length())
                .collect(Collectors.toCollection(ArrayList::new));
        this.randomFacts = new FileReader(new File("src/main/resources/random-facts.txt")).getTextList();
    }

    @Override
    public ProfanityResponse profanityCheck(ProfanityRequest profanityRequest) {
        Random random = new Random();
        String replacementPhrase = profanityRequest.getText().toLowerCase();
        for (String profanityWord : badWords) {
            if (replacementPhrase.contains(profanityWord)) {
                switch (profanityRequest.getAction()) {
                    case CENSOR:
                        replacementPhrase = censorProfanity(profanityWord, replacementPhrase);
                        continue;
                    case REPLACE_WITH_FACT:
                        replacementPhrase = randomFacts.get(random.nextInt(randomFacts.size()));
                        logger.info("Replaced whole phrase with random fact: " + replacementPhrase);
                        break;
                    case REPLACE_WITH_WORD:
                        if (Objects.nonNull(profanityRequest.getReplacementWord())) {
                            replacementPhrase = replaceWithWord(profanityWord, replacementPhrase, profanityRequest);
                        } else {
                            logger.warn("No replacement found, defaulting to censor");
                            replacementPhrase = censorProfanity(profanityWord, replacementPhrase);
                        }
                        continue;
                    default:
                        replacementPhrase = censorProfanity(profanityWord, replacementPhrase);
                        break;
                }
                break;
            }
        }
        logger.info("New phrase is: " + replacementPhrase);
        ProfanityResponse profanityResponse = new ProfanityResponse(replacementPhrase, profanityRequest.getAction());
        return profanityResponse;
    }

    private String censorProfanity(String profanityWord, String replacementPhrase) {
        char[] profanityCharArr = profanityWord.toCharArray();
        StringBuilder replacementText = new StringBuilder();
        for (char i : profanityCharArr) replacementText.append('*');
        return replacementPhrase.replace(profanityWord, replacementText.toString());
    }

    private String replaceWithWord(String profanityWord, String replacementPhrase, ProfanityRequest profanityRequest) {
        String replacementWord = profanityRequest.getReplacementWord();
        return replacementPhrase.replace(profanityWord, replacementWord);
    }

}
