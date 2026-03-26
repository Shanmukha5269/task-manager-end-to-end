package com.example.TaskManager.service;

import com.example.TaskManager.dto.AuthRequest;
import com.example.TaskManager.entity.User;

public interface UserService {

    String  register(AuthRequest request);
    String login(AuthRequest request);
    User findByUsername(String username);
}
