package com.demo.DemoApp.service.user;


import com.demo.DemoApp.data.dto.request.RegisterUserRequest;
import com.demo.DemoApp.data.dto.request.UploadImageRequest;
import com.demo.DemoApp.data.dto.response.OtpVerificationResponse;
import com.demo.DemoApp.data.dto.response.RegisterUserResponse;
import com.demo.DemoApp.data.model.User;
import com.demo.DemoApp.service.mail.EmailDetails;

public interface UserService {
    RegisterUserResponse register(RegisterUserRequest registerUserRequest, EmailDetails emailDetails);
    OtpVerificationResponse verifyOtp(EmailDetails emailDetails, String receivedOtp);
    String uploadImage(UploadImageRequest uploadImageRequest);
    User getUserById(String userId);
}
