package com.theokanning.openai.service;

import com.theokanning.openai.embedding.Embedding;
import com.theokanning.openai.embedding.EmbeddingRequest;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;


public class EmbeddingTest {

    OpenAiService service = new OpenAiService("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");

    @Test
    void createEmbeddings() {
        EmbeddingRequest embeddingRequest = EmbeddingRequest.builder()
                .model("text-similarity-babbage-001")
                .input(Collections.singletonList("The food was delicious and the waiter..."))
                .build();

        List<Embedding> embeddings = service.createEmbeddings(embeddingRequest).getData();
        System.out.println("embeddings = " + embeddings);
        assertFalse(embeddings.isEmpty());
        assertFalse(embeddings.get(0).getEmbedding().isEmpty());
    }
}
