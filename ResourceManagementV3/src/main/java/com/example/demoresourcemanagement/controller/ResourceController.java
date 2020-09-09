package com.example.demoresourcemanagement.controller;

import com.example.demoresourcemanagement.entity.Resource;
import com.example.demoresourcemanagement.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@RestController
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @PostMapping("/resource")
    void addResource(Resource resource) {
        resourceService.addResource(resource);
    }

    @PostMapping("/resources")
    public void addResourceList(List<Resource> resourceList) {
        resourceService.addResourceList(resourceList);
    }

    @GetMapping("/resources")
    public List<Resource> getAllResources() {
        return resourceService.getAllResources();
    }

    @GetMapping("/resource/{id}")
    public Resource getResourceById(@PathVariable Integer resourceId) {
        return resourceService.getResourceById(resourceId);
    }

    @GetMapping("/resource/{createDate}")
    public Resource getResourceByCreateDate(@PathVariable Date createDate) {
        return resourceService.getResourceByCreateDate(createDate);
    }

    @PutMapping("/resource/{resource}")
    public void setResourceById(@PathVariable Resource resource) {
        resourceService.setResourceById(resource);
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