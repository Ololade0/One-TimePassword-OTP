package com.demo.DemoApp.data.model;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.demo.DemoApp.utilities.DemoAppUtils.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document
public class User {
    @Id
    private String id;
    @NotNull(message = "field user name cannot be null")
    @NotEmpty(message = "field user name cannot be empty")
    @NotBlank(message = "field user name cannot be blank")
    @Pattern(regexp = NAME_REGEX, message = "user name must be letter starting with capital letter")
    private String userName;

    @NotNull(message = "field email cannot be null")
    @NotEmpty(message = "field email cannot be empty")
    @NotBlank(message = "field email cannot be blank")
    @Email(regexp = EMAIL_REGEX, message = "enter a valid email address")
    private String email;

    @NotNull(message = "field password cannot be null")
    @NotEmpty(message = "field password cannot be empty")
    @NotBlank(message = "field password cannot be blank")
    @Pattern(regexp = PASSWORD_REGEX, message = "Password must " +
            "contain at least one capital letter, one small letter, a number and special character(@$!%*?&)")

    private String password;
    private String imageUrl;
    private boolean isEnabled = false;
}
