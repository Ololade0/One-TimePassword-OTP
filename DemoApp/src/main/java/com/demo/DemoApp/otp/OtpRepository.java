package com.demo.DemoApp.otp;

import com.demo.DemoApp.data.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OtpRepository extends MongoRepository<Otp, String> {
    Otp findOtpByUser(User user);
    Otp findOtpByOtp(String otp);
}
