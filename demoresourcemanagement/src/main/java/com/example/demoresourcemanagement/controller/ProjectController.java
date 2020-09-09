package com.example.demoresourcemanagement.controller;

import com.example.demoresourcemanagement.entity.Project;
import com.example.demoresourcemanagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("")
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Integer id) {
        return projectService.getProjectById(id);
    }

//    @GetMapping("/{name}")
//    public ResponseEntity<Project> get(@PathVariable String name) {
//        return projectService.getProjectByName(name);
//    }

    @PostMapping("")
    public Project addProject(@RequestBody Project project) {
        projectService.addProject(project);
        return project;
    }

    @PutMapping("/{id}")
    public Project updateProject(@RequestBody Project project) {
        projectService.updateProject(project);
        return project;
    }

    @DeleteMapping("{id}")
    public String deleteProjectById(@PathVariable Integer id) {
        projectService.deleteProjectById(id);
        return "Deleted project with ID " +id;
    }

    @DeleteMapping("")
    public String deleteAll() {
        projectService.deleteAllProjects();
        return "Deleted all projects successfully!";
    }
}

