package com.demo.DemoApp.data.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UploadImageRequest {
    @NotNull(message = "field user id cannot be null")
    private String userId;

    @NotNull(message = "image file cannot be null")
    private MultipartFile profileImage;
}
