package com.petClinic.petClinic.core.session;

import com.petClinic.petClinic.commen.models.ProjectData;

import java.util.HashMap;
import java.util.Optional;

public class ProjectsManager {

    HashMap<Integer, ProjectData> projectDataHashMap;

    public ProjectsManager(){
        projectDataHashMap = new HashMap<>();
    }

    public Optional<ProjectData> getProjectData(int serialNum){

        ProjectData projectData = null;

        if(projectDataHashMap.containsKey(serialNum)){

            projectData = projectDataHashMap.get(serialNum);
        }

        return Optional.ofNullable(projectData);
    }

    public void addProjectData(ProjectData projectData){

        projectDataHashMap.put(projectData.getProject().getSerialNumber(), projectData);
    }
}
