package com.demo.DemoApp.service.cloudinary;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

import static com.demo.DemoApp.utilities.DemoAppUtils.TEST_IMAGE;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class CloudinaryServiceImplTest {
    @Autowired CloudinaryService cloudinaryService;

    private MultipartFile uploadTestImageFile(String imageUrl){
        try{
            return new MockMultipartFile("test_upload_image",
                    new FileInputStream(imageUrl));
        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }
    @Test
    void upload() {
        MultipartFile image = uploadTestImageFile(TEST_IMAGE);
        String response = cloudinaryService.upload(image);
        assertThat(response).isNotNull();
    }
}