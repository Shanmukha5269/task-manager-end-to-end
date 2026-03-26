package com.example.TaskManager.service;

import com.example.TaskManager.dto.TodoRequest;
import com.example.TaskManager.dto.TodoResponse;
import com.example.TaskManager.entity.Todo;
import com.example.TaskManager.entity.User;

import java.util.List;

public interface TodoService {

    List<TodoResponse> getTodos(User user);
    TodoResponse createTodo(TodoRequest request, User user);
    TodoResponse toggleTodo(Long id, User user);
}
