package com.toodooApp.toodoo.model;

import com.example.todo.model.Task;
import com.example.todo.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // 1. Task əlavə et
    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task.getTitle());
    }

    // 2. Bütün taskları göstər
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // 3. Task update et
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable int id, @RequestBody Task task) {
        return taskService.updateTask(id, task.getTitle(), task.isCompleted());
    }

    // 4. Task sil
    @DeleteMapping("/{id}")
    public boolean deleteTask(@PathVariable int id) {
        return taskService.deleteTask(id);
    }
}
