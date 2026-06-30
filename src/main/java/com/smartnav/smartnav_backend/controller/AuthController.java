package com.smartnav.smartnav_backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartnav.smartnav_backend.dto.SendOtpRequest;
import com.smartnav.smartnav_backend.dto.VerifyOtpRequest;
import com.smartnav.smartnav_backend.entity.User;
import com.smartnav.smartnav_backend.service.OtpService;
import com.smartnav.smartnav_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

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
public Map<String, Object> verifyOtp(
        @RequestBody VerifyOtpRequest request) {
    @PostMapping("/verify-otp")
    public Map<String, Object> verifyOtp(
            @RequestBody VerifyOtpRequest request) {

        Map<String, Object> response = new HashMap<>();

        boolean verified = otpService.verifyOtp(
                request.getMobile(),
                request.getOtp());

    Map<String, Object> response = new HashMap<>();

    if (verified) {

        Optional<User> user =
                userService.findByMobile(request.getMobile());

        response.put("message", "Login Success");

        if (user.isPresent()) {
            response.put("userId", user.get().getId());
            response.put("mobile", user.get().getMobile());
        }

        return response;
    }

    response.put("message", "Invalid OTP");
    return response;
}

        if (verified) {

            User user = userService
                    .getUserByMobile(request.getMobile())
                    .orElse(null);

            if (user == null) {

                User newUser = new User();
                newUser.setMobile(request.getMobile());
                newUser.setName("Guest User");
                newUser.setBio("Add your bio");
                newUser.setProfileImage(null);

                user = userService.saveUser(newUser);
            }

            response.put("message", "Login Success");
            response.put("userId", user.getId());
            response.put("mobile", user.getMobile());

            return response;
        }

        response.put("message", "Invalid OTP");
        return response;
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