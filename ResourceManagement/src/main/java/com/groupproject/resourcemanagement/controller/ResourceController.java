package com.groupproject.resourcemanagement.controller;


import com.groupproject.resourcemanagement.entity.ProjectEntity;
import com.groupproject.resourcemanagement.entity.ResourceEntity;
import com.groupproject.resourcemanagement.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


/**
 *
 *
 * Public void addResource(Resource resource) {} //
 * Public void addResourceList(List<Resource> resourceList)
 * * Public List<Resource> getResourceAll() {}  //
 * public List<Resource> getResourcesAllByProjectId(int projectId)
 * public boolean addResourceListToProject(List<Resource> resources)
 * public boolean removeResourceListFromProject(projectId, List<Resource> resource)
 *
 */

@Controller
public class ResourceController {

    @Autowired
    private  ResourceRepository resourceRepository;

    @GetMapping("/resources") // getResourceAll()
    public List<ResourceEntity> findAll() {
        return resourceRepository.findAll();
    }

    @PostMapping("/resources") // add resources
    public void add(@RequestBody ResourceEntity resource) {
        resourceRepository.save(resource);
    }

    @DeleteMapping("/resources/{id}") // delete resource
    public void delete(@PathVariable Integer id) {
        Optional<ResourceEntity> existResource = resourceRepository.findById(id);
        if (existResource.isPresent()) {
            resourceRepository.delete(existResource.get());
        }
    }

    @DeleteMapping("/resources") // delete all
    public void deleteAll() {
        resourceRepository.deleteAll();
    }

    @PutMapping("/resources/{id}") // update
    public ResponseEntity<?> update(@RequestBody ResourceEntity resource, @PathVariable Integer id) {
        Optional<ResourceEntity> existResource = resourceRepository.findById(id);
        if (existResource.isPresent()) {
            resourceRepository.save(existResource.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }









}
