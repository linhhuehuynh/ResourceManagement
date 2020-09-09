package com.example.demoresourcemanagement.controller;

import com.example.demoresourcemanagement.entity.Resource;
import com.example.demoresourcemanagement.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @PostMapping("/resource")
    void addResource(@RequestBody Resource resource) {
        resourceService.addResource(resource);
    }

    @PostMapping("/resources")
    public void addResourceList(@RequestBody List<Resource> resourceList) {
        resourceService.addResourceList(resourceList);
    }

    @GetMapping("/resources")
    public List<Resource> getAllResources() {
        return resourceService.getAllResources();
    }

    @GetMapping("/resource/{id}")
    public Resource getResourceById(@PathVariable Integer resourceId) {
        return resourceService.getResourceById(resourceId).get();
    }

//    @GetMapping("/resource/{createDate}")
//    public Resource getResourceByCreateDate(@PathVariable Date createDate) {
//        return resourceService.getResourceByCreateDate(createDate);
//    }

    @PutMapping("/resource/{id}")
    public ResponseEntity<?> setResourceById(@RequestBody Resource resource, @PathVariable Integer id) {
        Optional<Resource> existResource = resourceService.getResourceById(id);
        if (existResource.isPresent()) {
            resource.setId(id);
            resourceService.addResource(resource);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/resource/{resourceId}")
    public void deleteResourceById(@PathVariable int resourceId) {
        resourceService.deleteResourceById(resourceId);
    }

    @DeleteMapping("/reources")
    public void deleteAllResources() {
        resourceService.deleteAllResources();

    }

}
