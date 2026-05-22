package com.demo.Gym.controllers;

import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Gym.DTO.AuthRequest;
import com.demo.Gym.DTO.AuthResponse;
import com.demo.Gym.DTO.RegistrationRequest;

import com.demo.Gym.Security.JWTUtil;

import com.demo.Gym.models.AppUsers;
import com.demo.Gym.repositories.UserRepository;

@RestController
@RequestMapping("/gym/auth")
public class AuthController {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public AuthController(
            UserRepository userRepo,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JWTUtil jwtUtil
    ) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {

        AppUsers user = new AppUsers();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        if (request.getRole() == null || request.getRole().isEmpty()) {
            user.setRole("ROLE_USER");
        } else {
            user.setRole(request.getRole());
        }

        userRepo.save(user);

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String token = jwtUtil.generateToken(request.getEmail());

        return ResponseEntity.ok(new AuthResponse(token));
    }
}