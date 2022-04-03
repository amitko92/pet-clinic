package com.petClinic.petClinic.repository.project;

import com.petClinic.petClinic.entity.Project;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository {

    public Optional<Project> getProjectByProjectNumber(int serialNumber);

    public Optional<List<Project>> getAllProjects();
}
