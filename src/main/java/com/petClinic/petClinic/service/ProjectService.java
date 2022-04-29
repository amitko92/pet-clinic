package com.petClinic.petClinic.service;

import com.petClinic.petClinic.entity.Project;
import com.petClinic.petClinic.repository.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private HashMap<Integer, Project> projects = null;

    @Autowired
    public ProjectService(ProjectRepository projectRepository){

        this.projectRepository = projectRepository;
    }

    /**
     * returning Project corresponding to the given serial number.
     * the function using lazy loading from data base,
     * checking if the project is in the in memory system, if not is go to the data base.
     * @param serialNumber - the project's serial number.
     * @return Optional<Project> - Optional empty if not founds.
     */
    public Optional<Project> getProjectBySerialNumber(int serialNumber){
        Project  project= null;

        // in the first time, in-memory is null.
        if(projects == null) {

            projects = new HashMap<>();
        }

        // if not in in-memory, checking in data base.
        if(!projects.containsKey(serialNumber)) {

            // try to retrieve from data base.
            Optional<Project> optionalProject = this.projectRepository.getProjectByProjectNumber(serialNumber);

            // if retrieve from data base, put in in-memory.
            if(optionalProject.isPresent()) {

                this.projects.put(serialNumber, optionalProject.get());
            }

            return optionalProject;
        }

        return Optional.ofNullable(this.projects.get(serialNumber));
    }
}
