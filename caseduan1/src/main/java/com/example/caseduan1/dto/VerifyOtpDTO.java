package com.example.caseduan1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyOtpDTO {
    private String emailOrUsername;
    private String otp;
}
