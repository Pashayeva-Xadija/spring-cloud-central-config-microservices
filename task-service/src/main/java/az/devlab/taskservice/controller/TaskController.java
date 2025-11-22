package az.devlab.taskservice.controller;

import az.devlab.taskservice.dto.Task;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RefreshScope
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final List<Task> tasks = new CopyOnWriteArrayList<>();

    @Value("${task.max-tasks}")
    private int maxTasks;

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        if (tasks.size() >= maxTasks) {
            return ResponseEntity.badRequest()
                    .body("Max task limit reached: " + maxTasks);
        }
        tasks.add(task);
        return ResponseEntity.ok(task);
    }

    @GetMapping
    public List<Task> listTasks() {
        return tasks;
    }
}
