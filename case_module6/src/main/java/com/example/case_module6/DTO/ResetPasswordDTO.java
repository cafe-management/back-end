package com.example.case_module6.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordDTO {
    private String emailOrUsername;
    private String otp;
    private String newPassword;
}
