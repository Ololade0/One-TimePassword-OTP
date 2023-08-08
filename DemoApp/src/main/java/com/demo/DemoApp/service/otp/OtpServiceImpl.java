package com.demo.DemoApp.service.otp;

import com.demo.DemoApp.data.model.User;
import com.demo.DemoApp.otp.Otp;
import com.demo.DemoApp.otp.OtpRepository;
import com.demo.DemoApp.service.mail.EmailDetails;
import com.demo.DemoApp.service.mail.EmailService;
import com.demo.DemoApp.utilities.DemoAppUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OtpServiceImpl implements OtpService{

    private final OtpRepository otpRepository;



    @Override
    public String generateAndSaveOtp(User user) {
        Otp existingOtp = otpRepository.findOtpByUser(user);
        if(existingOtp != null)
            otpRepository.delete(existingOtp);
        String generatedOtp = DemoAppUtils.generateOtp();

        Otp otp = Otp.builder()
                .user(user)
                .otp(generatedOtp)
                .email(user.getEmail())
                .build();
        otpRepository.save(otp);
        return generatedOtp;

    }



    @Override
    public Otp validateOtp(String receivedOtp) {
        Otp otp = otpRepository.findOtpByOtp(receivedOtp);
        if(otp == null)
            throw new RuntimeException("Otp is invalid");
        else if(otp.getExpirationTime().isBefore(LocalDateTime.now())){
            otpRepository.delete(otp);
            throw new RuntimeException("Otp is expired");
        }
        return otp;
    }

    @Override
    public void deleteOtp(Otp otp) {

        otpRepository.delete(otp);
    }
}
