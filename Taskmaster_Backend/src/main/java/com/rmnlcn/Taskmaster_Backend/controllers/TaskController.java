package com.rmnlcn.Taskmaster_Backend.controllers;

import com.rmnlcn.Taskmaster_Backend.entities.Project;
import com.rmnlcn.Taskmaster_Backend.entities.Task;
import com.rmnlcn.Taskmaster_Backend.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Tasks", description = "API to manage tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @Operation(summary = "Get all tasks", description = "Retrieve all tasks from the database.")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a task by its ID", description = "Retrieve details of a specific task.")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskService.getTaskById(id);
        if (task.isPresent()) {
            return ResponseEntity.ok(task.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Create a new task", description = "Add a new task to the database.")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing task", description = "Update detail(s) of a specific task.")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        Optional<Task> task = taskService.getTaskById(id);
        if (task.isPresent()) {
            Task updatedTask = task.get();
            updatedTask.setDescription(taskDetails.getDescription());
            updatedTask.setDueDate(taskDetails.getDueDate());
            updatedTask.setCompleted(taskDetails.getCompleted());
            updatedTask.setName(taskDetails.getName());
            return ResponseEntity.ok(taskService.createTask(updatedTask));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a task by its ID", description = "Remove a task from the database.")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }
}
