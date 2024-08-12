package org.example.exercice2_architecture.notify;

@FunctionalInterface
public interface NotifyAction {
    void execute(String message);
}
