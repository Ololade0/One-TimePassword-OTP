package com.demo.DemoApp.data.dto.response;

import lombok.*;

@Builder
@Getter
public class RegisterUserResponse {
    private String id;
    private String userName;
    private String message;
}
