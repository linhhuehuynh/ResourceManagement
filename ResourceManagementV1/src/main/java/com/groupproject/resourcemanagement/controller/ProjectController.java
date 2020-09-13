package com.groupproject.resourcemanagement.controller;

import com.groupproject.resourcemanagement.entity.ProjectEntity;
import com.groupproject.resourcemanagement.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/projects")
    public List<ProjectEntity> findAll() {
        return projectRepository.findAll();
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<ProjectEntity> get(@PathVariable Integer id) {
        try {
            Optional<ProjectEntity> project = projectRepository.findById(id);
            return new ResponseEntity<ProjectEntity>(project.get(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<ProjectEntity>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/projects/{name}")
    public ResponseEntity<ProjectEntity> get(@PathVariable String name) {
        Optional<ProjectEntity> project = projectRepository.findByName(name);
        if (project.isPresent()) {
            return new ResponseEntity<ProjectEntity>(project.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/projects")
    public void add(@RequestBody ProjectEntity project) {
        projectRepository.save(project);
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity<?> update(@RequestBody ProjectEntity project, @PathVariable Integer id) {
        Optional<ProjectEntity> existProject = projectRepository.findById(id);
        if (existProject.isPresent()) {
            projectRepository.save(project);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/projects/{id}")
    public void delete(@PathVariable Integer id) {
        Optional<ProjectEntity> existProject = projectRepository.findById(id);
        if (existProject.isPresent()) {
            projectRepository.delete(existProject.get());
        }
    }

    @DeleteMapping("/projects")
    public void deleteAll() {
        projectRepository.deleteAll();
    }

}
