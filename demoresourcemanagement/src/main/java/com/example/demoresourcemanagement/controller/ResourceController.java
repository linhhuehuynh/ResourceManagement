package com.example.demoresourcemanagement.controller;

import com.example.demoresourcemanagement.entity.Resource;
import com.example.demoresourcemanagement.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @PostMapping("")
    void addResource(@RequestBody Resource resource) {
        resourceService.addResource(resource);
    }

    @PostMapping("/all")
    public void addResourceList(@RequestBody List<Resource> resourceList) {
        resourceService.addResourceList(resourceList);
    }

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<?> getAllResources() {
        return resourceService.getAllResources();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getResourceById(@PathVariable Integer resourceId) {
        return resourceService.getResourceById(resourceId);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> setResourceById(@RequestBody Resource resource, @PathVariable Integer id) {
        return resourceService.setResourceById(resource, id);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteResourceById(@PathVariable int id) {
        return resourceService.deleteResourceById(id);
    }

    @DeleteMapping("")
    @ResponseBody
    public ResponseEntity<?> deleteAllResources() {
        return resourceService.deleteAllResources();
    }

    @GetMapping("/project/{id}")
    @ResponseBody
    public ResponseEntity<?> getResourcesByProjectId(@PathVariable Integer id) {
        return resourceService.getResourcesByProjectId(id);
    }

    @PostMapping("/project/{id}")
    @ResponseBody
    public void addResourcesToProject(@RequestBody List<Resource> resourceList, @PathVariable Integer id) {
        resourceService.addResourcesToProject(resourceList, id);
    }

}
