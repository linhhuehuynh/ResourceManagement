package com.example.demoresourcemanagement.service;

import com.example.demoresourcemanagement.dao.ProjectDao;
import com.example.demoresourcemanagement.entity.Project;
import com.example.demoresourcemanagement.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
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

    public ResponseEntity<?> getProjectById(int id) {
        try {
            Optional<Project> project = projectDao.findById(id);
            return new ResponseEntity<>(project.get(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Project Not Found!", HttpStatus.NOT_FOUND);
        }
    }

    public Project addProject(Project project) {
        return projectDao.save(project);
    }

    public ResponseEntity<?> updateProject(Project project, Integer id) {
        Optional<Project> existProject = projectDao.findById(id);
        if (existProject.isPresent()) {
            project.setId(id);
            projectDao.save(project);
            return new ResponseEntity<>("Updated Project Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Project Not Found!", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> deleteProjectById(int id) {
        Optional<Project> existProject = projectDao.findById(id);
        if (existProject.isPresent()) {
            projectDao.deleteById(id);
            return new ResponseEntity<>("Deleted Project Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Project Not Found!", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> deleteAllProjects() {
        projectDao.deleteAll();
        return new ResponseEntity<>("Deleted Project Successfully", HttpStatus.OK);
    }

    public ResponseEntity<?> getProjectsByResourceId(int resouceId) {
        Specification<Project> specification = queryProjectCriteria(resouceId);
        List<Project> projects = projectDao.findAll(specification);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    private Specification<Project> queryProjectCriteria(int resouceId) {
        return new Specification<Project>() {
            @Override
            public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                Join<Resource, Project> join = root.join("resourceList", JoinType.LEFT);
                predicates.add(cb.equal(join.get("id"), resouceId));
                return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
    }

}
