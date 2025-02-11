package com.laserviewtechnologies.socialapp.service;

import com.laserviewtechnologies.socialapp.model.User;
import com.laserviewtechnologies.socialapp.repository.UserRepository;
import com.laserviewtechnologies.socialapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Register a new user (creating user with an encrypted password)
    public User registerUser(User user) throws Exception {
        // Check if the username already exists
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new Exception("Username already exists");
        }

        // Check if the email is already registered (optional)
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new Exception("Email is already registered");
        }

        // Encrypt the user's password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the new user to the database
        return userRepository.save(user);
    }

    // Authenticate a user by checking username and password
    public String authenticate(User user) throws Exception {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser.isPresent()) {
            // Verify if the password matches the stored encrypted password
            User userFromDb = existingUser.get();
            if (passwordEncoder.matches(user.getPassword(), userFromDb.getPassword())) {
                // Generate JWT token using the username (or user ID)
                return jwtUtil.generateToken(userFromDb.getUsername());  // FIXED
            } else {
                throw new Exception("Invalid credentials");
            }
        } else {
            throw new Exception("User not found");
        }
    }

    // Retrieve user details by username (optional method for user info retrieval)
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

