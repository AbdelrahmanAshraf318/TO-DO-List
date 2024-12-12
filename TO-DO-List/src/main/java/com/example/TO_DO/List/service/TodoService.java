package com.example.TO_DO.List.service;


import com.example.TO_DO.List.exception.ResourceNotFoundException;
import com.example.TO_DO.List.model.Todo;
import com.example.TO_DO.List.repository.TodoRepo;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TodoService
{
    private final TodoRepo todoRepo;

    public TodoService(TodoRepo todoRepo){
        this.todoRepo = todoRepo;
    }

    public List<Todo> getAllTodos(){
        return todoRepo.findAll();
    }

    public Todo createTodo(Todo todo){
        return todoRepo.save(todo);
    }

    public Todo updateTodo(UUID id, Todo todoDetails)
    {
        Todo todo = todoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found"));

        todo.setName(todoDetails.getName());
        todo.setCompleted(todoDetails.getCompleted());
        todo.setTask(todoDetails.getTask());

        return todoRepo.save(todo);
    }

    public void deleteTodoById(UUID id)
    {
        Todo todo = todoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found"));

        todoRepo.deleteById(id);
    }
}
