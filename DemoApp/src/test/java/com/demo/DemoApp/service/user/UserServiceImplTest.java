package com.demo.DemoApp.service.user;

import com.demo.DemoApp.data.dto.request.RegisterUserRequest;
import com.demo.DemoApp.data.dto.request.UploadImageRequest;
import com.demo.DemoApp.data.dto.response.OtpVerificationResponse;
import com.demo.DemoApp.data.dto.response.RegisterUserResponse;
import com.demo.DemoApp.data.model.User;
import org.junit.jupiter.api.BeforeEach;
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
class UserServiceImplTest {
    @Autowired
    UserService userService;
    RegisterUserResponse registerResponse;
    private UploadImageRequest uploadImageRequest;
    @BeforeEach
    void setUp() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUserName("Temx");
        registerUserRequest.setEmail("tem@gmail.com");
        registerUserRequest.setPassword("Password");
        registerResponse = userService.register(registerUserRequest, null);

        uploadImageRequest = new UploadImageRequest();
        uploadImageRequest.setUserId("1");
        uploadImageRequest.setProfileImage(uploadTestImageFile(TEST_IMAGE));
    }

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
    void register() {

        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUserName("Temx");
        registerUserRequest.setEmail("temx@gmail.com");
        registerUserRequest.setPassword("Password");
        registerResponse = userService.register(registerUserRequest, null);

        assertThat(registerResponse.getUserName()).isEqualTo(registerUserRequest.getUserName());
        System.out.println(registerResponse);
    }

//    @Test
//    void verifyOtp() {
//        OtpVerificationResponse verificationResponse = userService.verifyOtp(registerResponse.getOtp());
//        assertThat(verificationResponse.getMessage()).isEqualTo("Verification Successful");
//        assertThat(verificationResponse.isEnabled()).isEqualTo(true);
//        assertThat(verificationResponse.getOtp()).isEqualTo(registerResponse.getOtp());
//        System.out.println(verificationResponse);
//    }

    @Test
    void uploadProfileImageTest(){
        uploadImageRequest = new UploadImageRequest();
        uploadImageRequest.setUserId(registerResponse.getId());
        uploadImageRequest.setProfileImage(uploadTestImageFile(TEST_IMAGE));
        String response =
                userService.uploadImage(uploadImageRequest);
        assertThat(response).isEqualTo("Profile image uploaded");
    }

    @Test
    void getUserByIdTest(){
        User user = userService.getUserById(registerResponse.getId());
        assertThat(user.getUserName()).isEqualTo(registerResponse.getUserName());
        assertThat(user.getEmail()).isNotNull();
    }
}
