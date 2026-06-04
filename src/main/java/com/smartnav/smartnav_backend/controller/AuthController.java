package com.smartnav.smartnav_backend.controller;

import com.smartnav.smartnav_backend.entity.User;
import com.smartnav.smartnav_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

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