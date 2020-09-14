package com.example.demoresourcemanagement.controller;

import com.example.demoresourcemanagement.entity.ProjectColumn;
import com.example.demoresourcemanagement.service.ProjectColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projectcolumn")
public class ProjectColumnController {

    @Autowired
    ProjectColumnService projectColumnService;

    @GetMapping("/project/{projectId}")
    public ResponseEntity<?> getAllProjectColumns(@PathVariable int projectId) {
        return projectColumnService.getAllColumnsByProjectId(projectId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getColumnById(@PathVariable int id) {
        return projectColumnService.getProjectColumnById(id);
    }

    @PostMapping ("")
    public ProjectColumn createProjectColumn(@RequestBody ProjectColumn projectColumn) {
        return projectColumnService.createColumn(projectColumn);
    }

    @PutMapping("/{columnId}")
    public ResponseEntity<?> updateProjectColumn(@RequestBody ProjectColumn projectColumn, @PathVariable int columnId) {
        return projectColumnService.updateColumn(projectColumn, columnId);
    }

    @DeleteMapping("/{columnId}")
    public ResponseEntity<?> deleteProjectColumnById(@PathVariable int columnId) {
    return projectColumnService.deleteColumnById(columnId);
    }

//    @DeleteMapping("/project/{projectId}")
//    public ResponseEntity<?> deleteAllProjectColumns(@PathVariable int projectId) {
//        return projectColumnService.deleteAllColumnsByProjectId(projectId);
//    }
}