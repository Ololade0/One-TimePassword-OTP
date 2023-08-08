package com.demo.DemoApp.service.otp;

import com.demo.DemoApp.data.model.User;
import com.demo.DemoApp.otp.Otp;

public interface OtpService {
    String generateAndSaveOtp(User user);
    Otp validateOtp(String otp);
    void deleteOtp(Otp otp);
}
