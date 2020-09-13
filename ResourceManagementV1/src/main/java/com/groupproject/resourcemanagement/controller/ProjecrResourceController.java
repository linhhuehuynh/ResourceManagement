package com.groupproject.resourcemanagement.controller;

import com.groupproject.resourcemanagement.dao.ProjectResourceRepository;
import com.groupproject.resourcemanagement.entity.ProjectResource;
import com.groupproject.resourcemanagement.exception.NotFoundException;
import com.groupproject.resourcemanagement.service.ProjectResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class ProjecrResourceController {

    @Autowired
    private ProjectResourceRepository projectResourceRepository;
    private ProjectResourceService projectResourceService;

    @GetMapping("/projectresource/{projectId}/resources")
    public List<ProjectResource> getResourceListByProjectId(@PathVariable Integer projectId) {

        if(!projectResourceRepository.existsByProjectId(projectId)) {
            throw new NotFoundException("Project Not Found!");
        }
        return projectResourceRepository.findByProjectId(projectId);
    }

    @PostMapping("/projectresource/")
    public void addProjectResource(@RequestBody ProjectResource projectResource) {
        projectResourceService.addProjectResource(projectResource);
    }

    @DeleteMapping("/projectresource/{projectId}/projectresource/{resoucreid}")
    public String deleteResource(@PathVariable Integer projectId, @PathVariable Integer resourceId) {

        if(!projectResourceRepository.existsByProjectId(projectId)) {
            throw new NotFoundException("Project Not Found!");
        }
        return projectResourceRepository.findById(resourceId)
                .map(resource -> {
                    projectResourceRepository.delete(resource);
                    return "Deleted Successfully!";
                }).orElseThrow(() -> new NotFoundException("Resource List Not Found!"));
    }
}