package com.example.demoresourcemanagement.controller;

import com.example.demoresourcemanagement.entity.Project;
import com.example.demoresourcemanagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/projects")
    public List<Project> findAll() {
        return projectService.findAll();
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<Project> get(@PathVariable Integer id) {
        try {
            Optional<Project> project = projectService.findById(id);
            return new ResponseEntity<Project>(project.get(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("/projects/{name}")
//    public ResponseEntity<Project> get(@PathVariable String name) {
//        Optional<Project> project = projectService.findByName(name);
//        if (project.isPresent()) {
//            return new ResponseEntity<Project>(project.get(), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @PostMapping("/projects")
    public void add(@RequestBody Project project) {
        projectService.addProjet(project);
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity<?> update(@RequestBody Project project, @PathVariable Integer id) {
        Optional<Project> existProject = projectService.findById(id);
        if (existProject.isPresent()) {
            project.setId(id);
            projectService.addProjet(project);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/projects/{id}")
    public void delete(@PathVariable Integer id) {
        Optional<Project> existProject = projectService.findById(id);
        if (existProject.isPresent()) {
            projectService.delete(existProject.get().getId());
        }
    }

    @DeleteMapping("/projects")
    public void deleteAll() {
        projectService.deleteAll();
    }

}