package com.example.TaskManager.controller;

import com.example.TaskManager.dto.TodoRequest;
import com.example.TaskManager.dto.TodoResponse;
import com.example.TaskManager.entity.User;
import com.example.TaskManager.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;


    @PostMapping
    public TodoResponse createTodo(@RequestBody TodoRequest request,
                                         Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        return todoService.createTodo(request, user);
    }

    @GetMapping
    public List<TodoResponse> getTodos(Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        return todoService.getTodos(user);
    }

    @PutMapping("/{id}/toggle")
    public TodoResponse toggleTodo(@PathVariable Long id,
                                   Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        return todoService.toggleTodo(id, user);
    }
}
