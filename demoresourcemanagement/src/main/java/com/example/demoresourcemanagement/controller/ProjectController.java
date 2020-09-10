package com.example.demoresourcemanagement.controller;

import com.example.demoresourcemanagement.entity.Project;
import com.example.demoresourcemanagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("")
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getProjectsById(@PathVariable Integer id) {
        return projectService.getProjectById(id);
    }

    @PostMapping("")
    public void addProject(@RequestBody Project project) {
        projectService.addProject(project);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> updateProject(@RequestBody Project project, @PathVariable Integer id) {
        return projectService.updateProject(project, id);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable int id) {
        return projectService.deleteProjectById(id);
    }

    @DeleteMapping("")
    @ResponseBody
    public ResponseEntity<?> deleteAll() {
        return projectService.deleteAllProjects();
    }

    @GetMapping("/resource/{id}")
    @ResponseBody
    public ResponseEntity<?> getProjectsByResourceId(@PathVariable Integer id) {
        return projectService.getProjectsByResourceId(id);
    }
}