package com.example.resourcemanagement.controller;

import com.example.resourcemanagement.entity.ProjectColumn;
import com.example.resourcemanagement.service.ProjectColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projectcolumn")
public class ProjectColumnController {

    @Autowired
    ProjectColumnService projectColumnService;

    @GetMapping("/project/{projectId}")
    public ResponseEntity<?> getAllProjectColumns(@PathVariable int projectId) {
        Optional<List<ProjectColumn>> result = projectColumnService.getAllColumnsByProjectId(projectId);
        if(result == null) {
            return new ResponseEntity<>("Project Not Found!", HttpStatus.NOT_FOUND);
        }
        if(result.isPresent()) {return new ResponseEntity<>(result.get(), HttpStatus.OK);}
        return new ResponseEntity<>("No Columns In Project!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getColumnById(@PathVariable int id) {
        Optional<ProjectColumn> result = projectColumnService.getProjectColumnById(id);
        if(result.isPresent()) {return new ResponseEntity<>(result.get(), HttpStatus.OK);}
        return new ResponseEntity<>("Column Not Found!", HttpStatus.NOT_FOUND);
    }

    @PostMapping ("")
    public ResponseEntity<?> createProjectColumn(@RequestBody ProjectColumn projectColumn) {
        Optional<ProjectColumn> res = projectColumnService.createColumn(projectColumn);
        if(res.isPresent()) {
            return new ResponseEntity<>(res.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Project Not Found!", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{columnId}")
    public ResponseEntity<?> updateProjectColumn(@RequestBody ProjectColumn projectColumn, @PathVariable int columnId) {
        Optional<ProjectColumn> res = projectColumnService.updateColumn(projectColumn,columnId);
        if(res == null) {
            return new ResponseEntity<>("Project Not Found!", HttpStatus.NOT_FOUND);
        }
        if(res.isPresent()) {return new ResponseEntity<>(res.get(), HttpStatus.OK);}
        return new ResponseEntity<>("Column Not Found!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{columnId}")
    public ResponseEntity<?> deleteProjectColumnById(@PathVariable int columnId) {
        Optional<ProjectColumn> existProjectColumn = projectColumnService.deleteColumnById(columnId);
        if(existProjectColumn.isPresent()) {
            return new ResponseEntity<>("Deleted Column Successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Column Not Found!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/project/{projectId}")
    public ResponseEntity<?> deleteAllProjectColumns(@PathVariable int projectId) {
        Optional<String> res = projectColumnService.deleteAllColumnsByProjectId(projectId);
        if(res == null) {
            return new ResponseEntity<>("Project Not Found!", HttpStatus.OK);
        } else if(res.isPresent()) {
            return new ResponseEntity<>(res.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Columns In Project!", HttpStatus.OK);
        }
    }
}