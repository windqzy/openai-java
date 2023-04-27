package com.theokanning.openai.service;

import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionChunk;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.LogProbResult;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CompletionTest {

    //    String token = System.getenv("OPENAI_TOKEN");
//    String token = System.getenv("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");
    OpenAiService service = new OpenAiService("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");

    @Test
    void createCompletion() {
        CompletionRequest completionRequest = CompletionRequest.builder()
                .model("ada")
                .prompt("Somebody once told me the world is gonna roll me")
                .echo(true)
                .n(5)
                .maxTokens(50)
                .user("testing")
                .logitBias(new HashMap<>())
                .logprobs(5)
                .build();

        List<CompletionChoice> choices = service.createCompletion(completionRequest).getChoices();
        for (CompletionChoice choice : choices) {
            Integer index = choice.getIndex();
            String finishReason = choice.getFinish_reason();
            String text = choice.getText();
            LogProbResult logprobs = choice.getLogprobs();
            System.out.println("index = " + index + "     text = " + text);
        }
        assertEquals(5, choices.size());
        assertNotNull(choices.get(0).getLogprobs());
    }

    @Test
    void streamCompletion() {
        CompletionRequest completionRequest = CompletionRequest.builder()
                .model("ada")
                .prompt("Somebody once told me the world is gonna roll me")
                .echo(true)
                .n(1)
                .maxTokens(25)
                .user("testing")
                .logitBias(new HashMap<>())
                .logprobs(5)
                .stream(true)
                .build();

        List<CompletionChunk> chunks = new ArrayList<>();
        service.streamCompletion(completionRequest).blockingForEach(chunks::add);
        assertTrue(chunks.size() > 0);
        assertNotNull(chunks.get(0).getChoices().get(0));
    }
}
