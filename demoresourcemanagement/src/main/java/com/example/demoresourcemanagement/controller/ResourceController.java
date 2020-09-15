package com.example.demoresourcemanagement.controller;

import com.example.demoresourcemanagement.entity.Project;
import com.example.demoresourcemanagement.entity.Resource;
import com.example.demoresourcemanagement.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @PostMapping("")
    public ResponseEntity<?> addResource(@RequestBody Resource resource) {
        resourceService.addResource(resource);
        return new ResponseEntity<>("Added Resource Successfully!", HttpStatus.OK);
    }

    @PostMapping("/all")
    public ResponseEntity<?> addResourceList(@RequestBody List<Resource> resourceList) {
        resourceService.addResourceList(resourceList);
        return new ResponseEntity<>("Added Resources Successfully!", HttpStatus.OK);
    }

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<?> getAllResources() {
        List<Resource> resources = resourceService.getAllResources();
        if (resources.isEmpty()) {
            return new ResponseEntity<>("No resources!", HttpStatus.OK);
        }
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getResourceById(@PathVariable Integer resourceId) {
        Optional<Resource> resource = resourceService.getResourceById(resourceId);
        if (resource.isPresent()) {
            return new ResponseEntity<>(resource, HttpStatus.OK);
        }
        return new ResponseEntity<>("Resource Not Found!", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> setResourceById(@RequestBody Resource resource, @PathVariable Integer id) {
        Optional<Resource> existResource = resourceService.setResourceById(resource, id);
        if (existResource.isPresent()) {
            return new ResponseEntity<>("Updated resource Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Resource Not Found!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteResourceById(@PathVariable int id) {
        Optional<Resource> existResource = resourceService.deleteResourceById(id);
        if (existResource.isPresent()) {
            return new ResponseEntity<>("Deleted Resource Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Resource Not Found!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("")
    @ResponseBody
    public ResponseEntity<?> deleteAllResources() {
        resourceService.deleteAllResources();
        return new ResponseEntity<>("Deleted All Resources Successfully!", HttpStatus.OK);
    }

    @GetMapping("/project/{id}")
    @ResponseBody
    public ResponseEntity<?> getResourcesByProjectId(@PathVariable Integer id) {
        List<Resource> resources = resourceService.getResourcesByProjectId(id);
        if (resources.isEmpty()) {
            return new ResponseEntity<>("No Resources for this project!", HttpStatus.OK);
        }
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @PostMapping("/project/{id}")
    @ResponseBody
    public ResponseEntity<?> addResourcesToProject(@RequestBody List<Resource> resourceList, @PathVariable Integer id) {
        Optional<Project> project = resourceService.addResourcesToProject(resourceList, id);
        if (project.isPresent()) {
            return new ResponseEntity<>("Added Resource Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Project Not Found!", HttpStatus.NOT_FOUND);
    }
}
