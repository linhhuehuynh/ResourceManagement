package com.example.demoresourcemanagement.service;

import com.example.demoresourcemanagement.dao.ProjectDao;
import com.example.demoresourcemanagement.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImp implements ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Override
    public Project addProject(Project project) {
        return projectDao.save(project);
    }

//    @Override
//    public List<Project> saveAll(List<Project> projects) {
//        return projectDao.saveAll(projects);
//    }

    @Override
    public List<Project> findAll() {
        return projectDao.findAll();
    }

    @Override
    public Optional<Project> findById(int id) {
        return projectDao.findById(id);
    }

//    @Override
//    public Optional<Project> findByName(String name) {
//        return Optional.empty();
//    }

    @Override
    public void delete(int id) {
        projectDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        projectDao.deleteAll();
    }

//    @Override
//    public void delete(Project project) {
//        projectDao.delete(project);
//    }


}
