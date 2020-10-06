package com.example.resourcemanagement.controller;

import com.example.resourcemanagement.entity.ResourceColumn;
import com.example.resourcemanagement.service.ResourceColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/resourcecolumn")
public class ResourceColumnController {

    @Autowired
    private ResourceColumnService resourceColumnService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getColumnNameById(@PathVariable int id) {
        Optional<ResourceColumn> result = resourceColumnService.getColumnNameById(id);
        if (result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Column Not Found!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllResourceColumnName() {
        Optional<List<ResourceColumn>> result = resourceColumnService.getAllResourceColumnName();
        if (result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createResourceColumn(@RequestBody ResourceColumn resourceColumn) {
        Optional<ResourceColumn> res = resourceColumnService.createColumnName(resourceColumn);
        return new ResponseEntity<>(res.get(), HttpStatus.OK);
    }

    @PutMapping("/{columnId}")
    public ResponseEntity<?> updateResourceColumn(@RequestBody ResourceColumn resourceColumn, @PathVariable int columnId) {
        Optional<ResourceColumn> res = resourceColumnService.updateColumnName(resourceColumn, columnId);
        if (res.isPresent()) {
            return new ResponseEntity<>(res.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Column Name Not Found!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{columnId}")
    public ResponseEntity<?> deleteResourceColumnById(@PathVariable int columnId) {
        Optional<ResourceColumn> existResoureColumn = resourceColumnService.deleteColumnNameById(columnId);
        if (existResoureColumn.isPresent()) {
            return new ResponseEntity<>("Deleted Column Name Successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Column Name Not Found!", HttpStatus.NOT_FOUND);
    }
}
