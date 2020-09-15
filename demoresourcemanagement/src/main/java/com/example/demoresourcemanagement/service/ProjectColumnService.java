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

    public Optional<List<ProjectColumn>> getAllColumnsByProjectId(int id) {
        Optional<Project> project = projectDao.findById(id);
        Optional<List<ProjectColumn>> projectColumns = projectColumnDao.getAllByProjectId(id);

        if (project.isPresent() && projectColumns.isPresent())
        {
            return projectColumns;
        }
        return Optional.empty();
    }

    public Optional<ProjectColumn> getProjectColumnById(int id) {
            Optional<ProjectColumn> projectColumn = projectColumnDao.findById(id);
            if(projectColumn.isPresent()) {
                return projectColumn;
            }
            return Optional.empty();
    }

    public Optional<ProjectColumn> createColumn(ProjectColumn projectColumn) {
        ProjectColumn createdProjectColumn = projectColumnDao.save(projectColumn);
        Optional<ProjectColumn> result = Optional.of(createdProjectColumn);
        if(result.isPresent()) {
            return result;
        }
        return Optional.empty();
    }

    public Optional<ProjectColumn> updateColumn(ProjectColumn projectColumn, int id) {
        Optional<ProjectColumn> column = projectColumnDao.findById(id);
        if (column.isPresent()) {
            projectColumn.setId(id);
            ProjectColumn savedProjectColumn =  projectColumnDao.save(projectColumn);
            Optional<ProjectColumn> result = Optional.of(savedProjectColumn);
            return result;
        }
        return Optional.empty();

    }

    public Optional<ProjectColumn> deleteColumnById(int id) {
        Optional<ProjectColumn> column = projectColumnDao.findById(id);
        if (column.isPresent()) {
            projectColumnDao.deleteById(id);
            return Optional.of(column.get());
        }
        return Optional.empty();
    }

//    public Optional<List<ProjectColumn>> deleteAllColumnsByProjectId(int id) {
//        Optional<Project> project = projectDao.findById(id);
//        Optional<List<ProjectColumn>> projectColumns = projectColumnDao.getAllByProjectId(id);
//
//        if (project.isPresent() && projectColumns.isPresent()) {
//
//        }
//            return new ResponseEntity<>("Deleted All Columns!", HttpStatus.OK);
//        return new ResponseEntity<>("Project OR Columns Not Found!", HttpStatus.NOT_FOUND);
//    }
}


