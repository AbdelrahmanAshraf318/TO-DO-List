package com.example.TO_DO.List.controller;


import com.example.TO_DO.List.model.Todo;
import com.example.TO_DO.List.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/todos")
public class TodoController
{
    private final TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return todoService.createTodo(todo);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable UUID id, @RequestBody Todo todoDetails) {
        return todoService.updateTodo(id, todoDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodoById(@PathVariable UUID id) {
        todoService.deleteTodoById(id);
        return ResponseEntity.ok().build();
    }
}
