package com.theokanning.openai.service;

import com.theokanning.openai.model.Model;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class ModelTest {
    OpenAiService service = new OpenAiService("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");

    @Test
    void listModels() {
        List<Model> models = service.listModels();
        models.forEach(model -> {
            System.out.println("model = " + model);
        });

        assertFalse(models.isEmpty());
    }

    @Test
    void getModel() {
        Model ada = service.getModel("ada");

        System.out.println("ada.getId() = " + ada.getId());
        assertEquals("ada", ada.id);
        assertEquals("openai", ada.ownedBy);
        assertFalse(ada.permission.isEmpty());
    }
}
