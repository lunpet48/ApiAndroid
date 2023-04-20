package com.android.api.dto;

import lombok.Data;

@Data
public class ResetPasswordDto {
    private String code;
    private String password;
    private String repeatPassword;
}
