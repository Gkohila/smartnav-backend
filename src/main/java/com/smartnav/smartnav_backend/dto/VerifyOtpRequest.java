package com.smartnav.smartnav_backend.dto;

import lombok.Data;

@Data
public class VerifyOtpRequest {

    private String mobile;
    private String otp;
}