package com.demo.DemoApp.controller;

import com.demo.DemoApp.data.dto.request.RegisterUserRequest;
import com.demo.DemoApp.data.dto.request.UploadImageRequest;
import com.demo.DemoApp.data.dto.response.OtpVerificationResponse;
import com.demo.DemoApp.data.dto.response.RegisterUserResponse;
import com.demo.DemoApp.data.model.User;
import com.demo.DemoApp.service.mail.EmailDetails;
import com.demo.DemoApp.service.user.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/demo/user/")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserRequest registerUserRequest, EmailDetails emailDetails){
        RegisterUserResponse registerResponse = userService.register(registerUserRequest, emailDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(registerResponse);
    }

    @PostMapping("verify")
    public ResponseEntity<?> verifyOtp(@Valid EmailDetails emailDetails, @RequestParam String otp){
        OtpVerificationResponse otpVerificationResponse = userService.verifyOtp(emailDetails, otp);
        return ResponseEntity.ok(otpVerificationResponse);
    }

    @PostMapping(value = "upload_image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImage(@Valid @ModelAttribute UploadImageRequest uploadImageRequest){
        String response = userService.uploadImage(uploadImageRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<?> getUserById(@Valid @PathVariable String id){
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}
