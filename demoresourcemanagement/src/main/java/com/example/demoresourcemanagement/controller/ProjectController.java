package com.example.demoresourcemanagement.controller;

import com.example.demoresourcemanagement.dao.ProjectDao;
import com.example.demoresourcemanagement.entity.Project;
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
    private ProjectDao projectRepository;

    @GetMapping("/projects")
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<Project> get(@PathVariable Integer id) {
        try {
            Optional<Project> project = projectRepository.findById(id);
            return new ResponseEntity<Project>(project.get(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/projects/{name}")
    public ResponseEntity<Project> get(@PathVariable String name) {
        Optional<Project> project = projectRepository.findByName(name);
        if (project.isPresent()) {
            return new ResponseEntity<Project>(project.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/projects")
    public Project add(@RequestBody Project project) {

        projectRepository.save(project);
        return project;
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity<?> update(@RequestBody Project project, @PathVariable Integer id) {
        Optional<Project> existProject = projectRepository.findById(id);
        if (existProject.isPresent()) {
            projectRepository.save(project);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/projects/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<Project> existProject = projectRepository.findById(id);
        if (existProject.isPresent()) {
            projectRepository.delete(existProject.get());
        }
        return "Deleted Project with ID " + id;
    }

    @DeleteMapping("/projects")
    public String deleteAll() {
        projectRepository.deleteAll();
        return "Deleted all projects!";
    }

}

