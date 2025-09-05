package com.toodooApp.toodoo.model;

import com.example.todo.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public Task addTask(String title) {
        Task task = new Task(nextId++, title, false);
        tasks.add(task);
        return task;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task updateTask(int id, String title, boolean completed) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setTitle(title);
                task.setCompleted(completed);
                return task;
            }
        }
        return null; // tapılmadısa
    }

    public boolean deleteTask(int id) {
        return tasks.removeIf(task -> task.getId() == id);
    }
}
