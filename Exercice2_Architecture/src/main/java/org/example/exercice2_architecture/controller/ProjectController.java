package org.example.exercice2_architecture.controller;

import org.example.exercice2_architecture.service.ProjectService;
//import org.example.exercice2_architecture.service.TaskService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {


    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/update-status")
    public void updateTaskStatus(@RequestParam String taskId,
                                 @RequestParam String newStatus) {
        projectService.updateTaskStatus(taskId, newStatus, message -> {
            // Logique de notification, par exemple envoyer un email ou un message
            System.out.println("Notification: " + message);
        });
    }

    /*private final TaskService taskService;

    public ProjectController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PutMapping("/{id}/status")
    public void updateTaskStatus(@PathVariable Long id, @RequestParam String status) {
        taskService.updateTaskStatus(id, status);
    }

    @PostMapping("/{id}/assign")
    public void assignTask(@PathVariable Long id, @RequestParam Long userId) {
        taskService.assignTask(id, userId);
    }*/
}
