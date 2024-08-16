package org.example.projectservice.infrastructure.repository;

import org.example.projectservice.domain.model.Project;
import org.example.projectservice.domain.repository.ProjectRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProjectRepository extends JpaRepository<Project, Long>, ProjectRepository {
}
