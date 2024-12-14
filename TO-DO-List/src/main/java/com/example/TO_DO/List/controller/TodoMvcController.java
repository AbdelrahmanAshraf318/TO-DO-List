package com.example.TO_DO.List.controller;

import com.example.TO_DO.List.exception.ResourceNotFoundException;
import com.example.TO_DO.List.model.Todo;
import com.example.TO_DO.List.repository.TodoRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;


@Controller
@RequestMapping("/todos")
public class TodoMvcController
{
    private final TodoRepo todoRepository;

    public TodoMvcController(TodoRepo todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping
    public String viewTodos(Model model) {
        model.addAttribute("todos", todoRepository.findAll());
        return "todos"; // View name (e.g., todos.jsp or todos.html)
    }

    @GetMapping("/new")
    public String showTodoForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "addTodo"; // View name for the form
    }

    @PostMapping
    public String addTodo(@ModelAttribute Todo todo) {
        todo.setCreatedAt(LocalDateTime.now().now());  // Set the current time here
        if (todo.getCompleted() == null) {  // Check if 'completed' was not sent in the form
            todo.setCompleted(false);  // Set it to false by default if not checked
        }
        todoRepository.save(todo);
        return "redirect:/todos"; // Redirect to the list view
    }

    @GetMapping("/{id}/edit")
    public String showEditTodoForm(@PathVariable UUID id, Model model) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found"));
        model.addAttribute("todo", todo);
        return "editTodo"; // Resolves to /WEB-INF/jsp/editTodo.jsp
    }

    @PostMapping("/{id}/update")
    public String updateTodo(@PathVariable UUID id, @ModelAttribute Todo updatedTodo) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found"));
        todo.setCreatedAt(LocalDateTime.now().now());  // Set the current time here
        todo.setName(updatedTodo.getName());
        todo.setTask(updatedTodo.getTask());
        todo.setCompleted(updatedTodo.getCompleted());  // Handle completed status
        todoRepository.save(todo);
        return "redirect:/todos";
    }

    // **Handle Deletion**
    @DeleteMapping("/{id}/delete")
    public String deleteTodo(@PathVariable UUID id) {
        todoRepository.deleteById(id);
        return "redirect:/todos"; // Redirect back to the list after deletion
    }
}
