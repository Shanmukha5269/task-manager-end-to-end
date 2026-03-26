package com.example.TaskManager.controller;

import com.example.TaskManager.dto.AuthRequest;
import com.example.TaskManager.dto.AuthResponse;
import com.example.TaskManager.entity.Role;
import com.example.TaskManager.entity.User;
import com.example.TaskManager.repository.UserRepository;
import com.example.TaskManager.security.JwtUtil;
import com.example.TaskManager.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request) {

        return userService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        return new AuthResponse(userService.login(request));
    }
}
