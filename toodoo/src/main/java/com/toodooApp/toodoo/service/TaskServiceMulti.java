package com.toodooApp.toodoo.model;

import com.example.todo.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class TaskServiceMulti {
    private final List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    // Thread pool (4 worker thread)
    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    public Future<Task> addTask(String title) {
        return executor.submit(() -> {
            Task task = new Task(nextId++, title, false);
            tasks.add(task);
            return task;
        });
    }

    public Future<List<Task>> getAllTasks() {
        return executor.submit(() -> tasks);
    }

    public Future<Task> updateTask(int id, String title, boolean completed) {
        return executor.submit(() -> {
            for (Task task : tasks) {
                if (task.getId() == id) {
                    task.setTitle(title);
                    task.setCompleted(completed);
                    return task;
                }
            }
            return null;
        });
    }

    public Future<Boolean> deleteTask(int id) {
        return executor.submit(() -> tasks.removeIf(task -> task.getId() == id));
    }
}
