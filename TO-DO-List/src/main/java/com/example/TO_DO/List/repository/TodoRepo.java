package com.example.TO_DO.List.repository;

import com.example.TO_DO.List.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TodoRepo extends JpaRepository<Todo, UUID> {
}
