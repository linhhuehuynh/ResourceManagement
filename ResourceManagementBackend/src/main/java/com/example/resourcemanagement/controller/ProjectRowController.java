package com.example.resourcemanagement.controller;

import com.example.resourcemanagement.entity.ProjectRow;
import com.example.resourcemanagement.service.ProjectRowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projectrow")
public class ProjectRowController {

    @Autowired
    private ProjectRowService projectRowService;

    @GetMapping("/project/{projectId}")
    public ResponseEntity<?> getAllProjectRowByProjectId(@PathVariable int projectId) {
        Optional<List<ProjectRow>> result = projectRowService.getAllRowsByProjectId(projectId);
        if (result == null) {
            return new ResponseEntity<>("Project Not Found!", HttpStatus.NOT_FOUND);
        }
        if(result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("No Rows In This Project", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectRowById(@PathVariable int id) {
        Optional<ProjectRow> result = projectRowService.getProjectRowById(id);
        if(result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Row Not Found!", HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<?> createProjectRow(@RequestBody ProjectRow projectRow) {
        Optional<ProjectRow> result = projectRowService.createRow(projectRow);
        if(result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Project Not Found!", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProjectRow(@RequestBody ProjectRow projectRow, @PathVariable int id) {
        Optional<ProjectRow> result = projectRowService.updateProjectRow(projectRow, id);
        if(result == null) {
            return new ResponseEntity<>("Project Not Found!", HttpStatus.NOT_FOUND);
        }
        if(result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Row Not Found!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProjectRow(@PathVariable int id) {
        Optional<ProjectRow> result = projectRowService.deleteProjectRowById(id);
        if(result.isPresent()) {
            return new ResponseEntity<>("Deleted Row Successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Row Not Found!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/project/{projectId}")
    public ResponseEntity<?> deleteAllProjectRows(@PathVariable int projectId) {
        Optional<String> res = projectRowService.deleteAllRowByProjectId(projectId);
        if(res == null) {
            return new ResponseEntity<>("Project Not Found!", HttpStatus.OK);
        } else if(res.isPresent()) {
            return new ResponseEntity<>(res.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Rows In Project!", HttpStatus.OK);
        }
    }
}
