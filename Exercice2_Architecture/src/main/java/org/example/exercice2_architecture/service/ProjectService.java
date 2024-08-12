package org.example.exercice2_architecture.service;

import org.example.exercice2_architecture.annotation.Notify;
import org.example.exercice2_architecture.notify.NotifyAction;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    public void updateTaskStatus(String taskId, String newStatus, NotifyAction notificationAction) {
        System.out.println("Task " + taskId + " status updated to " + newStatus);


        notificationAction.execute("Task " + taskId + " status updated to " + newStatus);
    }

   /* @Notify
    public void updateTaskStatus(Long taskId, String status) {
        System.out.println("Statut de la tâche mis à jour : Task ID = " + taskId + ", Status = " + status);
    }

    @Notify
    public void assignTask(Long taskId, Long userId) {
        System.out.println("Tâche assignée : Task ID = " + taskId + ", User ID = " + userId);
    }*/
}
