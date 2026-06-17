package com.smartnav.smartnav_backend.controller;

import com.smartnav.smartnav_backend.dto.SendOtpRequest;
import com.smartnav.smartnav_backend.dto.VerifyOtpRequest;
import com.smartnav.smartnav_backend.entity.User;
import com.smartnav.smartnav_backend.service.OtpService;
import com.smartnav.smartnav_backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;    

    @Autowired
    private OtpService otpService;

    @PostMapping("/send-otp")
    public String sendOtp(@RequestBody SendOtpRequest request) {

        return otpService.sendOtp(request.getMobile());
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestBody VerifyOtpRequest request) {

        boolean verified = otpService.verifyOtp(
                request.getMobile(),
                request.getOtp()
        );

        if (verified) {
            return "Login Success";
        }

        return "Invalid OTP";
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {

        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        return userService.loginUser(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }
}