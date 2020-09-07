package com.groupproject.resourcemanagement.service;

import com.groupproject.resourcemanagement.dao.ProjectResourceRepository;
import com.groupproject.resourcemanagement.entity.ProjectResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectResourceService {

    @Autowired
    private ProjectResourceRepository projectResourceRepository;

    public void addProjectResource(ProjectResource projectResource) {
        projectResourceRepository.save(projectResource);
    }
}

