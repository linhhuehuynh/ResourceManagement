package com.example.resourcemanagement.service;

import com.example.resourcemanagement.dao.ProjectDao;
import com.example.resourcemanagement.dao.ProjectRowDao;
import com.example.resourcemanagement.entity.Project;
import com.example.resourcemanagement.entity.ProjectRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectRowService {

    @Autowired
    private ProjectRowDao projectRowDao;

    @Autowired
    private ProjectDao projectDao;

    public Optional<List<ProjectRow>> getAllRowsByProjectId(int id) {
        Optional<Project> project = projectDao.findById(id);
        if (!project.isPresent()) {
            return null;
        }
        return projectRowDao.getAllByProjectId(id);
    }

    public Optional<ProjectRow> getProjectRowById(int id) {
        return projectRowDao.findById(id);
    }

    public Optional<ProjectRow> createRow(ProjectRow projectRow) {
        Optional<Project> project = projectDao.findById(projectRow.getProject().getId());
        if(!project.isPresent()) {
            return Optional.empty();
        }
        ProjectRow createdProjectRow = projectRowDao.save(projectRow);
        Optional<ProjectRow> result = Optional.of(createdProjectRow);
        return result;
    }

    public Optional<ProjectRow> updateProjectRow(ProjectRow projectRow, int id) {
        Optional<Project> project = projectDao.findById(projectRow.getProject().getId());
        if(!project.isPresent()) {
            return null;
        }
        Optional<ProjectRow> row = projectRowDao.findById(id);
        if(row.isPresent()) {
            projectRow.setId(id);
            ProjectRow savedProjectRow = projectRowDao.save(projectRow);
            Optional<ProjectRow> result = Optional.of(savedProjectRow);
            return result;
        }
        return Optional.empty();
    }

    public Optional<ProjectRow> deleteProjectRowById(int id) {
        Optional<ProjectRow> row = projectRowDao.findById(id);
        if(row.isPresent()) {
            projectRowDao.deleteById(id);
            return Optional.of(row.get());
        }
        return Optional.empty();
    }
}
