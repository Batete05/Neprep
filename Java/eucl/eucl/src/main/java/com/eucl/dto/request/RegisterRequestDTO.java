package com.eucl.dto.request;

import com.eucl.common.ValidRwandanPhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6)
    @ValidRwandanPhoneNumber
    private String phone;

    @NotBlank
//    @ValidRwandaId
    private String nationalID;


    @NotBlank
    @Size(min = 8)
    private String password;
}
