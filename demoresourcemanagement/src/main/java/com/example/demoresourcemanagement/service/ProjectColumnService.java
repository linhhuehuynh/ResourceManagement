package com.example.demoresourcemanagement.service;

import com.example.demoresourcemanagement.dao.ProjectColumnDao;
import com.example.demoresourcemanagement.dao.ProjectDao;
import com.example.demoresourcemanagement.entity.Project;
import com.example.demoresourcemanagement.entity.ProjectColumn;
import org.hibernate.mapping.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProjectColumnService {

    @Autowired
    private ProjectColumnDao projectColumnDao;

    @Autowired
    private ProjectDao projectDao;

    public ResponseEntity<?> getAllColumnsByProjectId(int id) {
        Optional<Project> project = projectDao.findById(id);
        Optional<List<ProjectColumn>> projectColumns = projectColumnDao.getAllByProjectId(id);

        if (project.isPresent() && projectColumns.isPresent())
            return new ResponseEntity<>(projectColumns, HttpStatus.OK);
        return new ResponseEntity<>("Project Not Found!", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getProjectColumnById(int id) {
        try {
            Optional<ProjectColumn> projectColumn = projectColumnDao.findById(id);
            return new ResponseEntity<>(projectColumn.get(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Column Not Found!", HttpStatus.NOT_FOUND);
        }
    }

    public ProjectColumn createColumn(ProjectColumn projectColumn) {
        return projectColumnDao.save(projectColumn);
    }

    public ResponseEntity<?> updateColumn(ProjectColumn projectColumn, int id) {
        Optional<ProjectColumn> column = projectColumnDao.findById(id);
        if (column.isPresent()) {
            projectColumn.setId(id);
            projectColumnDao.save(projectColumn);
            return new ResponseEntity<>(projectColumn, HttpStatus.OK);
        }
        return new ResponseEntity<>("Column Not Found!", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> deleteColumnById(int id) {
        Optional<ProjectColumn> column = projectColumnDao.findById(id);
        if (column.isPresent()) {
            projectColumnDao.deleteById(id);
            return new ResponseEntity<>("Deleted Column Successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Column Not Found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> deleteAllColumnsByProjectId(int id) {
        Optional<Project> project = projectDao.findById(id);
        Optional<List<ProjectColumn>> projectColumns = projectColumnDao.getAllByProjectId(id);

        if (project.isPresent() && projectColumns.isPresent())
            return new ResponseEntity<>("Deleted All Columns!", HttpStatus.OK);
        return new ResponseEntity<>("Project OR Columns Not Found!", HttpStatus.NOT_FOUND);
    }
}


