package com.demo.DemoApp.service.user;

import com.demo.DemoApp.data.dto.request.RegisterUserRequest;
import com.demo.DemoApp.data.dto.request.UploadImageRequest;
import com.demo.DemoApp.data.dto.response.OtpVerificationResponse;
import com.demo.DemoApp.data.dto.response.RegisterUserResponse;
import com.demo.DemoApp.data.model.User;
import com.demo.DemoApp.data.repository.UserRepository;
import com.demo.DemoApp.otp.Otp;
import com.demo.DemoApp.service.cloudinary.CloudinaryService;
import com.demo.DemoApp.service.mail.EmailDetails;
import com.demo.DemoApp.service.mail.EmailService;
import com.demo.DemoApp.service.otp.OtpService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl  implements UserService{
    private final UserRepository userRepository;
    private final OtpService otpService;
    private final ModelMapper modelMapper;
    private final EmailService emailService;
    private final CloudinaryService cloudinaryService;
    @Override
    public RegisterUserResponse register(RegisterUserRequest registerUserRequest, EmailDetails emailDetails) {
//        checkIfUserAlreadyExists(registerUserRequest.getEmail());
        User user = modelMapper.map(registerUserRequest, User.class);
        User savedUser = userRepository.save(user);
        String otp = otpService.generateAndSaveOtp(savedUser);
        log.info("\n::::::::::::::: GENERATED OTP ->(%s) :::::::::::::::\n".formatted(otp));

        sendOtpMail(savedUser,emailDetails, otp);

        return RegisterUserResponse.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .message("Kindly check yor mail, to activate your email address")
                .build();
    }
    private void sendOtpMail(User user, EmailDetails emailDetails, String otp){
//        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(user.getEmail());
        emailDetails.setMsgBody("Hi" + "" + user.getUserName() +
               "\n To activate your  Account, please verify your email address.  with the OTP sent to your emailaddress \n" +
                "Your account will not be created until your email address is confirmed. \n \n \n Kindly input the OTP" + "" + otp);
        emailDetails.setSubject("Email Verification");
        emailService.sendSimpleMail(emailDetails);

    }


    private void checkIfUserAlreadyExists(String email) {
        User user = userRepository.findUserByEmail(email);
        if(user != null)
            throw new RuntimeException("User with this email already exists");
    }

    @Override
    public OtpVerificationResponse verifyOtp(EmailDetails emailDetails, String otp) {
        if(otp == null)
            throw new RuntimeException("Otp cannot be null");
        Otp receivedOtp =  otpService.validateOtp(otp);
        User user = receivedOtp.getUser();
        if(user.isEnabled()){
            otpService.deleteOtp(receivedOtp);
            throw new RuntimeException("User is already enabled");
        }
        else {
            user.setEnabled(true);
            userRepository.save(user);
            otpService.deleteOtp(receivedOtp);
            sendOtpVerificationMail(user,emailDetails);

            return OtpVerificationResponse.builder()
                    .message("Verification Successful")
                    .isEnabled(user.isEnabled())
                    .build();
        }
    }

    private void sendOtpVerificationMail(User user, EmailDetails emailDetails){
//        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(user.getEmail());
        emailDetails.setMsgBody("Hi" + "" + user.getUserName() +
                "\n Your account has been successfully verified");
        emailDetails.setSubject("Email Verification");
        emailService.sendSimpleMail(emailDetails);

    }

    @Override
    public String uploadImage(UploadImageRequest uploadImageRequest) {
        User user = getUserById(uploadImageRequest.getUserId());
        String imageUrl = cloudinaryService.upload(uploadImageRequest.getProfileImage());
        user.setImageUrl(imageUrl);
        userRepository.save(user);
        return "Profile image uploaded";
    }



    @Override
    public User getUserById(String userId) {




        return userRepository.findById(userId).orElseThrow(
                ()-> new RuntimeException("User not found"));
    }
}
