
package com.example.caseduan1.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordRequest {
    private String username;
    private String oldPassword;
    private String newPassword;
}
