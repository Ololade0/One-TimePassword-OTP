package com.demo.DemoApp.data.dto.request;

import com.demo.DemoApp.service.mail.EmailDetails;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.demo.DemoApp.utilities.DemoAppUtils.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RegisterUserRequest {

    private String userName;

    private String email;


    private String password;
}
