package com.smartnav.smartnav_backend.service;

import com.smartnav.smartnav_backend.entity.User;
import com.smartnav.smartnav_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smartnav.smartnav_backend.security.JwtService;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {

        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }
    @Autowired
    private JwtService jwtService;

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