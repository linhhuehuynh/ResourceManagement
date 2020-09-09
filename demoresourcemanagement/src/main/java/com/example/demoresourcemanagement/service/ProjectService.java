package com.example.demoresourcemanagement.service;

import com.example.demoresourcemanagement.dao.ProjectDao;
import com.example.demoresourcemanagement.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectDao projectDao;

    public List<Project> getAllProjects() {
        return projectDao.findAll();
    }

    public ResponseEntity<Project> getProjectById(int id) {
        try {
            Optional<Project> project = projectDao.findById(id);
            return new ResponseEntity<Project>(project.get(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
        }
    }



    //Do we really need this? When we choose the project name on website, it's just the UI part. Clicking the project
    //means the UI will match whatever we click to the correct ID. I don't think this is necessary - It create conflict with
    //getProjectById in Postman
//    public ResponseEntity<Project> getProjectByName(String name) {
//        Optional<Project> project = projectDao.findByName(name);
//        if (project.isPresent()) {
//            return new ResponseEntity<Project>(project.get(), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    public Project addProject(Project project) {
        return projectDao.save(project);
    }

    public Project updateProject(Project project) {
        return projectDao.save(project);
    }

    public void deleteProjectById(int id){
        projectDao.deleteById(id);
    }

    public void deleteAllProjects() {
        projectDao.deleteAll();
    }
}

