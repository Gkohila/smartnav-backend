package com.smartnav.smartnav_backend.controller;

import com.smartnav.smartnav_backend.dto.SendOtpRequest;
import com.smartnav.smartnav_backend.dto.VerifyOtpRequest;
import com.smartnav.smartnav_backend.entity.User;
import com.smartnav.smartnav_backend.service.OtpService;
import com.smartnav.smartnav_backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
public Map<String, Object> verifyOtp(
        @RequestBody VerifyOtpRequest request) {

    boolean verified = otpService.verifyOtp(
            request.getMobile(),
            request.getOtp()
    );

    Map<String, Object> response = new HashMap<>();

    if (verified) {

        Optional<User> user =
                userService.findByMobile(
                        request.getMobile());
        System.out.println("USER FOUND = " + user.isPresent());

        response.put("message", "Login Success");

        if (user.isPresent()) {

    System.out.println("USER ID = " + user.get().getId());
    System.out.println("MOBILE = " + user.get().getMobile());

    response.put("userId",
            user.get().getId());

    response.put("mobile",
            user.get().getMobile());
}
else {

    System.out.println("USER NOT FOUND");
}
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