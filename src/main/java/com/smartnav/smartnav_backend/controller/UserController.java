package com.smartnav.smartnav_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartnav.smartnav_backend.entity.User;
import com.smartnav.smartnav_backend.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Get user by ID
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {

        return userRepository.findById(id)
                .orElse(null);
    }

    // Update basic user details
    @PutMapping("/{id}")
    public User updateUser(
            @PathVariable Long id,
            @RequestBody User user) {

        User existingUser =
                userRepository.findById(id)
                        .orElse(null);

        if (existingUser != null) {

            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setMobile(user.getMobile());

            return userRepository.save(existingUser);
        }

        return null;
    }

    // Get profile using mobile number
    @GetMapping("/mobile/{mobile}")
    public User getUserByMobile(
            @PathVariable String mobile) {

        return userRepository
                .findByMobile(mobile)
                .orElse(null);
    }

    // Update profile
    @PutMapping("/profile/{mobile}")
    public User updateProfile(
            @PathVariable String mobile,
            @RequestBody User user) {

        User existingUser =
                userRepository
                        .findByMobile(mobile)
                        .orElse(null);

        if (existingUser != null) {

            existingUser.setName(user.getName());
            existingUser.setBio(user.getBio());
            existingUser.setProfileImage(
                    user.getProfileImage());

            return userRepository.save(
                    existingUser);
        }

        return null;
    }
}