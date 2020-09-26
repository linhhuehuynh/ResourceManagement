package com.example.resourcemanagement.controller;

import com.example.resourcemanagement.entity.Project;
import com.example.resourcemanagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("")
    public ResponseEntity<?> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        if (projects.isEmpty()) {
            return new ResponseEntity<>("No Project!", HttpStatus.OK);
        }
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getProjectsById(@PathVariable Integer id) {
        Optional<Project> project = projectService.getProjectById(id);
        if (project.isPresent()) {
            return new ResponseEntity<>(project, HttpStatus.OK);
        }
        return new ResponseEntity<>("Project Not Found!", HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<?> addProject(@RequestBody Project project) {
        projectService.addProject(project);
        return new ResponseEntity<>("Added Project Successfully!", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> updateProject(@RequestBody Project project, @PathVariable Integer id) {
        Optional<Project> existProject = projectService.updateProject(project, id);
        if (existProject.isPresent()) {
            return new ResponseEntity<>("Updated Project Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Project Not Found!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteProject(@PathVariable int id) {
        Optional<Project> existProject = projectService.deleteProjectById(id);
        if (existProject.isPresent()) {
            return new ResponseEntity<>("Deleted Project Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Project Not Found!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("")
    @ResponseBody
    public ResponseEntity<?> deleteAll() {
        projectService.deleteAllProjects();
        return new ResponseEntity<>("Deleted All Projects Successfully!", HttpStatus.OK);
    }

    @GetMapping("/resource/{id}")
    @ResponseBody
    public ResponseEntity<?> getProjectsByResourceId(@PathVariable Integer id) {
        List<Project> projects = projectService.getProjectsByResourceId(id);
        if (projects.isEmpty()) {
            return new ResponseEntity<>("No project for this resource!", HttpStatus.OK);
        }
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
}