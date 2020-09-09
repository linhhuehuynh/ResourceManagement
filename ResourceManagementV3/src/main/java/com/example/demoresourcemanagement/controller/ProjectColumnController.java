package com.example.demoresourcemanagement.controller;

import com.example.demoresourcemanagement.entity.ProjectColumn;
import com.example.demoresourcemanagement.service.ProjectColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projectcolumn")
public class ProjectColumnController {
    @Autowired
    ProjectColumnService projectColumnService;

        @GetMapping("")
        public List<ProjectColumn> getAllProjectColumns() {
            return projectColumnService.getAllColumns();
        }

        @PostMapping ("")
        public ProjectColumn createProjectColumn(@RequestBody ProjectColumn projectColumn) {
            return projectColumnService.createColumn(projectColumn);
        }

        @GetMapping("/{id}")
        public ProjectColumn getProjectColumn(@PathVariable int id) {
            return projectColumnService.getColumnById(id);
        }

    @PutMapping("")
    public ProjectColumn updateProjectColumn(@RequestBody ProjectColumn projectColumn) {
        projectColumnService.updateColumn(projectColumn);
        return projectColumn;
    }

        @DeleteMapping("/{id}")
        public String deleteProjectColumnById(@PathVariable int id) {
            projectColumnService.deleteColumnById(id);
            return "Deleted column ID " + id;
        }
    }