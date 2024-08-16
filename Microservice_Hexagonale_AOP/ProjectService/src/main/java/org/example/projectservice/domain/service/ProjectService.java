package org.example.projectservice.domain.service;

import org.example.projectservice.domain.model.Project;

import java.util.Optional;

public interface ProjectService {

    Optional<Project> findProjectById(Long id);
    Project createProject(Project project);
    void deleteProject(Long id);
}
