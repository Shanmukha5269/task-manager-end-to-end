package com.example.TaskManager.service;

import com.example.TaskManager.dto.TodoRequest;
import com.example.TaskManager.dto.TodoResponse;
import com.example.TaskManager.entity.Todo;
import com.example.TaskManager.entity.User;
import com.example.TaskManager.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService{

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public List<TodoResponse> getTodos(User user) {

        return todoRepository.findByUserId(user.getId())
                .stream()
                .map(t -> new TodoResponse(
                        t.getId(),
                        t.getTitle(),
                        t.isCompleted()
                ))
                .toList();
    }

    @Override
    public TodoResponse createTodo(TodoRequest request, User user) {

        Todo todo = new Todo();
        todo.setTitle(request.getTitle());
        todo.setUserId(user.getId());
        todo.setCompleted(false);

        Todo saved = todoRepository.save(todo);

        return new TodoResponse(
                saved.getId(),
                saved.getTitle(),
                saved.isCompleted()
        );
    }

    @Override
    public TodoResponse toggleTodo(Long id, User user) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        if (!todo.getUserId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        todo.setCompleted(!todo.isCompleted());

        Todo updated = todoRepository.save(todo);

        return new TodoResponse(
                updated.getId(),
                updated.getTitle(),
                updated.isCompleted()
        );
    }
}
