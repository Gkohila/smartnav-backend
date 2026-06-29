package com.smartnav.smartnav_backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartnav.smartnav_backend.entity.User;
import com.smartnav.smartnav_backend.repository.UserRepository;
import com.smartnav.smartnav_backend.security.JwtService;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    public User registerUser(User user) {

        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    public String loginUser(User user) {

        Optional<User> existingUser =
                userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {

            if (existingUser.get().getPassword()
                    .equals(user.getPassword())) {

                return "Login Success";
            }
        }

        return "Invalid Email or Password";
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findByMobile(String mobile) {
    return userRepository.findByMobile(mobile);
}
    
}