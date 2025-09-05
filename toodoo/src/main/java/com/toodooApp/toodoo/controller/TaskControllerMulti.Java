package com.toodooApp.toodoo.model;

import com.example.todo.model.Task;
import com.example.todo.service.TaskServiceMulti;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/multi/tasks")
public class TaskControllerMulti {
    private final TaskServiceMulti taskServiceMulti;

    public TaskControllerMulti(TaskServiceMulti taskServiceMulti) {
        this.taskServiceMulti = taskServiceMulti;
    }

    // 1. Task əlavə et
    @PostMapping
    public Task addTask(@RequestBody Task task) throws ExecutionException, InterruptedException {
        Future<Task> future = taskServiceMulti.addTask(task.getTitle());
        return future.get();
    }

    // 2. Bütün taskları göstər
    @GetMapping
    public List<Task> getAllTasks() throws ExecutionException, InterruptedException {
        Future<List<Task>> future = taskServiceMulti.getAllTasks();
        return future.get();
    }

    // 3. Task update et
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable int id, @RequestBody Task task) throws ExecutionException, InterruptedException {
        Future<Task> future = taskServiceMulti.updateTask(id, task.getTitle(), task.isCompleted());
        return future.get();
    }

    // 4. Task sil
    @DeleteMapping("/{id}")
    public boolean deleteTask(@PathVariable int id) throws ExecutionException, InterruptedException {
        Future<Boolean> future = taskServiceMulti.deleteTask(id);
        return future.get();
    }
}
