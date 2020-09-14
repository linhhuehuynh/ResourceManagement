package com.example.demoresourcemanagement.service;

import com.example.demoresourcemanagement.dao.ProjectDao;
import com.example.demoresourcemanagement.dao.ResourceDao;
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
public class ResourceService {
    @Autowired
    ResourceDao resourceDao;

    @Autowired
    ProjectDao projectDao;

    public void addResource(Resource resource) {
        resourceDao.save(resource);
    }

    public void addResourceList(List<Resource> resourceList) {
        resourceDao.saveAll(resourceList);
    }

    public ResponseEntity<?> getAllResources() {
        try {
            List<Resource> resourceList = resourceDao.findAll();
            return new ResponseEntity<>(resourceList, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No Resource List Found!", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> getResourceById(int resourceId) {
        try {
            Optional<Resource> resource = resourceDao.findById(resourceId);
            return new ResponseEntity<>(resource.get(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Resource Not Found!", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> setResourceById(Resource resource, int resourceId) {
        Optional<Resource> existResource = resourceDao.findById(resourceId);
        if (existResource.isPresent()) {
            resource.setId(resourceId);
            resourceDao.save(resource);
            return new ResponseEntity<>("Updated Resource Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Resource Not Found!", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> deleteResourceById(int resourceId) {
        Optional<Resource> existResource = resourceDao.findById(resourceId);
        if (existResource.isPresent()) {
            resourceDao.deleteById(resourceId);
            return new ResponseEntity<>("Deleted Resource Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("User Not Found!", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> deleteAllResources() {
        resourceDao.deleteAll();
        return new ResponseEntity<>("Deleted Resources Successfully", HttpStatus.OK);
    }

    public ResponseEntity<?> getResourcesByProjectId(int projectId) {
        Specification<Resource> specification = queryResourceCriteria(projectId);
        List<Resource> resources = resourceDao.findAll(specification);
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    private Specification<Resource> queryResourceCriteria(int projectId) {
        return new Specification<Resource>() {
            @Override
            public Predicate toPredicate(Root<Resource> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                Join<Project, Resource> join = root.join("projectList", JoinType.LEFT);
                predicates.add(cb.equal(join.get("id"), projectId));
                return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
    }

    public void addResourcesToProject(List<Resource> resourceList, int projectId) {
        Optional<Project> existProject = projectDao.findById(projectId);
        if (!existProject.isPresent()) {
            return;
        }

        Project project = existProject.get();
        for (Resource resource : resourceList) {
            project.addResource(resource);
        }

        resourceDao.saveAll(resourceList);
        projectDao.save(project);
    }

}
