package com.herschaft.ExpenseTracker.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.herschaft.ExpenseTracker.DTO.LoginRequest;
import com.herschaft.ExpenseTracker.DTO.RegisterRequest;
import com.herschaft.ExpenseTracker.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
public class AuthController {

    private final AuthenticationManager authManager;
    private final HttpSessionSecurityContextRepository securityContextRepository;
    private final UserService userService;

    AuthController(AuthenticationManager authManager, HttpSessionSecurityContextRepository securityContextRepository, UserService userService) {
        this.authManager = authManager;
        this.securityContextRepository = securityContextRepository;
        this.userService = userService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> authLogin(@RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {
        try {
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            Authentication currentAuth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

            context.setAuthentication(currentAuth);

            securityContextRepository.saveContext(context, request, response);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Map.of("message", "Authentication succeeded"));
        } catch (AuthenticationException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Wrong credentials"));
        }

    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> authRegister(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("WIP");
    }

}
