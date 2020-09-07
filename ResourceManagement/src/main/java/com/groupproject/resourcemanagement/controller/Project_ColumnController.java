package com.groupproject.resourcemanagement.controller;

import com.groupproject.resourcemanagement.entity.ProjectColumnEntity;
import com.groupproject.resourcemanagement.service.Project_ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/projectcolumn")
public class Project_ColumnController {
    @Autowired
    Project_ColumnService project_columnService;

    @GetMapping(value = "project/{id}")
    public List<ProjectColumnEntity> getAllProjectColumn(@PathVariable Integer id) {
        return project_columnService.findList(id);
    }

    @PostMapping
    public ProjectColumnEntity postProjectColumn(@RequestBody ProjectColumnEntity projectColumnEntity) {
        return project_columnService.create(projectColumnEntity);
    }

    @GetMapping(value = "/{id}")
    public ProjectColumnEntity getProjectColumn(@PathVariable Integer id) {
        return project_columnService.findOne(id);
    }

    @PostMapping(value = "/{id}")
    public boolean updateProjectColumn(@RequestBody ProjectColumnEntity projectColumnToUpdate, @PathVariable Integer id) {
        return project_columnService.update(projectColumnToUpdate, id);
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteProjectColumn(@PathVariable Integer id) {
        return project_columnService.delete(id);
    }
}



