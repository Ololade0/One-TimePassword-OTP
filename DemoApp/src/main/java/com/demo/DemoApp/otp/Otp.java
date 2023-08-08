package com.demo.DemoApp.otp;

import com.demo.DemoApp.data.model.User;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Otp {
    @Id
    private String id;
    @DBRef
    private User user;
    private String otp;
    private String email;



    private  LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime expirationTime = createdAt.plusSeconds(30);


}
