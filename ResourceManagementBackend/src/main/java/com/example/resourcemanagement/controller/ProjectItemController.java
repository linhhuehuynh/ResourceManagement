package com.example.resourcemanagement.controller;

import com.example.resourcemanagement.entity.ProjectItem;
import com.example.resourcemanagement.service.ProjectItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projectitem")
public class ProjectItemController {

    @Autowired
    private ProjectItemService projectItemService;

    @GetMapping("/row/{rowId}")
    public ResponseEntity<?> getAllProjectItemsByProjectRowId(@PathVariable int rowId) {
        Optional<List<ProjectItem>> result = projectItemService.getAllProjectItemListByProjectRowId(rowId);
        if (result == null) {
                        return new ResponseEntity<>("Project Row Not Found!", HttpStatus.NOT_FOUND);
                    }
                    if(result.isPresent()) {
                        return new ResponseEntity<>(result.get(), HttpStatus.OK);
                    }
                    return new ResponseEntity<>("No Items In This Project Row", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/col/{colId}")
    public ResponseEntity<?> getAllProjectItemsByProjectColumnId(@PathVariable int colId) {
        Optional<List<ProjectItem>> result = projectItemService.getAllProjectItemListByProjectColumnId(colId);
        if (result == null) {
            return new ResponseEntity<>("Project Column Not Found!", HttpStatus.NOT_FOUND);
        }
        if(result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("No Items In This Project Column", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/rowcol/{rowId}/{colId}")
    public ResponseEntity<?> getProjectItemByProjectRowAndColumnId(@PathVariable int rowId, @PathVariable int colId) {
        Optional<ProjectItem> result = projectItemService.getProjectItemByProjectRowAndColumnId(rowId, colId);
        if (result == null) {
            return new ResponseEntity<>("Project Row Or Column Not Found!", HttpStatus.NOT_FOUND);
        }
        if(result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Item Not Found!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectItemById(@PathVariable int id) {
        Optional<ProjectItem> result = projectItemService.getProjectItemById(id);
        if(result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Item Not Found!", HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<?> createProjectItem(@RequestBody ProjectItem projectItem) {
        Optional<ProjectItem> result = projectItemService.createProjectItem(projectItem);
        if(result == null) {
            return new ResponseEntity<>("Project Row Or Column Not Found!", HttpStatus.NOT_FOUND);
        }
        if(result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("This Place Has Been Occupied!", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProjectItem(@RequestBody ProjectItem projectItem, @PathVariable int id) {
        Optional<ProjectItem> result = projectItemService.updateProjectItem(projectItem, id);
        if(result == null) {
            return new ResponseEntity<>("Project Row Or Column Not Found Or This Place Has Been Occupied!", HttpStatus.NOT_FOUND);
        }
        if(result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Item Not Found!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProjectItem(@PathVariable int id) {
        Optional<ProjectItem> result = projectItemService.deleteProjectItemById(id);
        if(result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Item Not Found!", HttpStatus.NOT_FOUND);
    }
}
