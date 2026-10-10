package com.herschaft.ExpenseTracker.service;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.herschaft.ExpenseTracker.DTO.RegisterRequest;
import com.herschaft.ExpenseTracker.exception.NotUniqueRegisterUsername;
import com.herschaft.ExpenseTracker.model.User;
import com.herschaft.ExpenseTracker.persistence.UserRepository;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    private void validateUsernameAvailable(RegisterRequest registerRequest) {
        if (userRepository.checkUsername(registerRequest.username())) {
            throw new NotUniqueRegisterUsername();
        }
    }

    public void registerUser(RegisterRequest registerRequest) {
        validateUsernameAvailable(registerRequest);
        User user = new User(registerRequest.username(), UUID.randomUUID(), passwordEncoder.encode(registerRequest.password()));
        userRepository.registerNewUser(user);
    }

}
