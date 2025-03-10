package com.example.case_module6.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyOtpDTO {
    private String emailOrUsername;
    private String otp;
}
