package com.example.resourcemanagement.service;

import com.example.resourcemanagement.dao.ProjectDao;
import com.example.resourcemanagement.entity.Project;
import com.example.resourcemanagement.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectDao projectDao;

    public List<Project> getAllProjects() {
        return projectDao.findAll();
    }

    public Optional<Project> getProjectById(int id) {
        return projectDao.findById(id);
    }

    public void addProject(Project project) {
        projectDao.save(project);
    }

    public Optional<Project> updateProject(Project project, Integer id) {
        Optional<Project> existProject = projectDao.findById(id);
        if (existProject.isPresent()) {
            project.setId(id);
            projectDao.save(project);
        }
        return existProject;
    }

    public Optional<Project> deleteProjectById(int id) {
        Optional<Project> existProject = projectDao.findById(id);
        if (existProject.isPresent()) {
            projectDao.deleteById(id);
        }
        return existProject;
    }

    public void deleteAllProjects() {
        projectDao.deleteAll();
    }

    public List<Project> getProjectsByResourceId(int resouceId) {
        Specification<Project> specification = queryProjectCriteria(resouceId);
        return projectDao.findAll(specification);
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
