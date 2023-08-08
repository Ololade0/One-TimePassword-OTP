package com.demo.DemoApp.service.cloudinary;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
    String upload(MultipartFile image);
}
