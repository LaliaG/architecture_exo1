package org.example.projectservice.domain.repository;

import org.example.projectservice.domain.model.Project;

import java.util.Optional;

public interface ProjectRepository {

    Optional<Project> findById(Long id);
    Project save(Project project);
    void deleteById(Long id);
}
