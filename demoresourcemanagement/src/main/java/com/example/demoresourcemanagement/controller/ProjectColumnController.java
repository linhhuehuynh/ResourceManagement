package com.example.demoresourcemanagement.controller;

import com.example.demoresourcemanagement.entity.ProjectColumn;
import com.example.demoresourcemanagement.service.ProjectColumnService;
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
        if(result.isPresent()) {return new ResponseEntity<>(result.get(), HttpStatus.OK);}
        return new ResponseEntity<>("Project Not Found!", HttpStatus.NOT_FOUND);
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
        return new ResponseEntity<>(res.get(), HttpStatus.OK);
//        return new ResponseEntity<>("Column Not Created!", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{columnId}")
    public ResponseEntity<?> updateProjectColumn(@RequestBody ProjectColumn projectColumn, @PathVariable int columnId) {
        Optional<ProjectColumn> res = projectColumnService.updateColumn(projectColumn,columnId);
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

//    @DeleteMapping("/project/{projectId}")
//    public ResponseEntity<?> deleteAllProjectColumns(@PathVariable int projectId) {
//        return projectColumnService.deleteAllColumnsByProjectId(projectId);
//    }
}