package com.example.TO_DO.List.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Todo
{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(columnDefinition = "BINARY(16)", unique = true, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;


    private String task; // this for the description of the task

    @Column(nullable = false)
    private Boolean completed = false;

    @CreationTimestamp // Automatically sets the value to the current timesamp
    @Column(updatable = false) //Ensures that the value is never updated
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime createdAt;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTask() {
        return task;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
