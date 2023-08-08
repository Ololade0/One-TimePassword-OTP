package com.demo.DemoApp.data.dto.response;

import lombok.*;

@Builder
@Getter
public class OtpVerificationResponse {
    private String message;
    private boolean isEnabled;
}
