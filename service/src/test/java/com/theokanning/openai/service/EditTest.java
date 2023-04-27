package com.theokanning.openai.service;

import com.theokanning.openai.OpenAiHttpException;
import com.theokanning.openai.edit.EditRequest;
import com.theokanning.openai.edit.EditResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EditTest {

    OpenAiService service = new OpenAiService("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");

    @Test
    void edit() throws OpenAiHttpException {
        EditRequest request = EditRequest.builder()
                .model("text-davinci-edit-001")
                .input("What day of the wek is it?")
                .instruction("Fix the spelling mistakes")
                .build();

        EditResult result = service.createEdit(request);
        System.out.println("result = " + result);
        assertNotNull(result.getChoices().get(0).getText());
    }
}
