package org.example.projectservice.application.service;

import org.example.projectservice.domain.model.Project;
import org.example.projectservice.domain.repository.ProjectRepository;
import org.example.projectservice.domain.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectApplicationService implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectApplicationService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Optional<Project> findProjectById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
