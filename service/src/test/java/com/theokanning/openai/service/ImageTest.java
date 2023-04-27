package com.theokanning.openai.service;

import com.theokanning.openai.image.CreateImageEditRequest;
import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.image.CreateImageVariationRequest;
import com.theokanning.openai.image.Image;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ImageTest {

//    static String filePath = "src/test/resources/penguin.png";
//    static String fileWithAlphaPath = "src/test/resources/penguin_with_alpha.png";
//    static String maskPath = "src/test/resources/mask.png";

    static String filePath = "src/test/resources/penguin.png";
    static String fileWithAlphaPath = "src/test/resources/penguin_with_alpha.png";
    static String maskPath = "src/test/resources/mask.png";

    OpenAiService service = new OpenAiService("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");

    @Test
    void createImageUrl() {
//        CreateImageRequest createImageRequest = CreateImageRequest.builder().prompt("penguin").n(3).size("256x256").user("testing").build();
        CreateImageRequest createImageRequest = CreateImageRequest.builder().prompt("一只可爱动漫小猫咪的头顶开出花朵，当你说你如此的爱我").n(3).size("256x256").user("testing").build();

        List<Image> images = service.createImage(createImageRequest).getData();
        for (Image image : images) {
            System.out.println("image.getUrl() = " + image.getUrl());
        }
        assertEquals(3, images.size());
        assertNotNull(images.get(0).getUrl());
    }

    @Test
    void createImageBase64() {
        CreateImageRequest createImageRequest = CreateImageRequest.builder().prompt("penguin").responseFormat("b64_json").user("testing").build();

        List<Image> images = service.createImage(createImageRequest).getData();
        for (Image image : images) {
            String b64Json = image.getB64Json();
            System.out.println("b64Json = " + b64Json);
        }
        assertEquals(1, images.size());
        assertNotNull(images.get(0).getB64Json());
    }

    @Test
    void createImageEdit() {
        CreateImageEditRequest createImageRequest = CreateImageEditRequest.builder().prompt("a penguin with a red background").responseFormat("url").size("256x256").user("testing").n(2).build();

        List<Image> images = service.createImageEdit(createImageRequest, fileWithAlphaPath, null).getData();
        for (Image image : images) {
            System.out.println("image.getUrl() = " + image.getUrl());
        }
        assertEquals(2, images.size());
        assertNotNull(images.get(0).getUrl());
    }

    @Test
    void createImageEditWithMask() {
        CreateImageEditRequest createImageRequest = CreateImageEditRequest.builder().prompt("a penguin with a red hat").responseFormat("url").size("256x256").user("testing").n(2).build();

        List<Image> images = service.createImageEdit(createImageRequest, filePath, maskPath).getData();
        for (Image image : images) {
            System.out.println("image.getUrl() = " + image.getUrl());
        }
        assertEquals(2, images.size());
        assertNotNull(images.get(0).getUrl());
    }

    @Test
    void createImageVariation() {
        CreateImageVariationRequest createImageVariationRequest = CreateImageVariationRequest.builder().responseFormat("url").size("256x256").user("testing").n(2).build();

        List<Image> images = service.createImageVariation(createImageVariationRequest, filePath).getData();
        for (Image image : images) {
            System.out.println("image.getUrl() = " + image.getUrl());
        }
        assertEquals(2, images.size());
        assertNotNull(images.get(0).getUrl());
    }
}
