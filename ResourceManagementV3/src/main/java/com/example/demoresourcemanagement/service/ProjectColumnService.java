package com.example.demoresourcemanagement.service;

import com.example.demoresourcemanagement.dao.ProjectColumnDao;
import com.example.demoresourcemanagement.entity.ProjectColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectColumnService {

    @Autowired
    private ProjectColumnDao projectColumnDao;

    public List<ProjectColumn> getAllColumns() {
        return projectColumnDao.findAll();
    }

    public ProjectColumn createColumn(ProjectColumn projectColumn) {
        return projectColumnDao.save(projectColumn);
    }

    public ProjectColumn getColumnById(int id) {
        return projectColumnDao.getOne(id);
    }

    public ProjectColumn updateColumn(ProjectColumn projectColumn) {
        return projectColumnDao.save(projectColumn);
    }

    public void deleteColumnById(int id) {
        projectColumnDao.deleteById(id);
    }


}
