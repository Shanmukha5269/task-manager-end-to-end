package com.example.TaskManager.repository;

import com.example.TaskManager.dto.TodoResponse;
import com.example.TaskManager.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<TodoResponse> findByUserId(Long userId);
}
