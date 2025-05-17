package com.eucl.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LoginRequestDTO {

    @Email
    @NotBlank(message = "valid email needed")
    private String email;

//    @Pattern(
//            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#^()_+=\\-])[A-Za-z\\d@$!%*?&#^()_+=\\-]{8,}$",
//            message = "Password must be at least 8 characters and include upper and lower case letters, a number, and a special character."
//    )
    @NotBlank(message = "password is required")
    @Size(min = 8)
    private String password;
}
