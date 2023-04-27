package com.theokanning.openai.service;

import com.theokanning.openai.moderation.Moderation;
import com.theokanning.openai.moderation.ModerationRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class ModerationTest {

    OpenAiService service = new OpenAiService("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");

    @Test
    void createModeration() {
        ModerationRequest moderationRequest = ModerationRequest.builder()
                .input("I want to kill them")
                .model("text-moderation-latest")
                .build();

        Moderation moderationScore = service.createModeration(moderationRequest).getResults().get(0);
        System.out.println("moderationScore = " + moderationScore);

        assertTrue(moderationScore.isFlagged());
    }
}
