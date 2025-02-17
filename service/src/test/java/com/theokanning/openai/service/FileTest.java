package com.theokanning.openai.service;

import com.theokanning.openai.DeleteResult;
import com.theokanning.openai.file.File;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FileTest {
    static String filePath = "src/test/resources/fine-tuning-data.jsonl";

    OpenAiService service = new OpenAiService("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");
    static String fileId;

    @Test
    @Order(1)
    void uploadFile() throws Exception {
        File file = service.uploadFile("fine-tune", filePath);
        fileId = file.getId();

        assertEquals("fine-tune", file.getPurpose());
        assertEquals(filePath, file.getFilename());

        // wait for file to be processed
        TimeUnit.SECONDS.sleep(10);
    }

    @Test
    @Order(2)
    void listFiles() {
        List<File> files = service.listFiles();

        assertTrue(files.stream().anyMatch(file -> file.getId().equals(fileId)));
    }

    @Test
    @Order(3)
    void retrieveFile() {
        File file = service.retrieveFile(fileId);

        assertEquals(filePath, file.getFilename());
    }

    @Test
    @Order(4)
    void deleteFile() {
        DeleteResult result = service.deleteFile(fileId);
        assertTrue(result.isDeleted());
    }
}
