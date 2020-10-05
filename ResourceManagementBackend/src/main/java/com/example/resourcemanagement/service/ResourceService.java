package com.example.resourcemanagement.service;

import com.example.resourcemanagement.dao.ProjectDao;
import com.example.resourcemanagement.dao.ResourceDao;
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

    public List<Resource> getAllResources() {
        return resourceDao.findAll();
    }

    public Optional<Resource> getResourceById(int id) {
        return resourceDao.findById(id);
    }

    public Optional<Resource> setResourceById(Resource resource, int resourceId) {
        Optional<Resource> existResource = resourceDao.findById(resourceId);
        if (existResource.isPresent()) {
            resource.setId(resourceId);
            resourceDao.save(resource);;
        }
        return existResource;
    }

    public Optional<Resource> deleteResourceById(int resourceId) {
        Optional<Resource> existResource = resourceDao.findById(resourceId);
        if (existResource.isPresent()) {
            resourceDao.deleteById(resourceId);
        }
        return existResource;
    }

    public void deleteAllResources() {
        resourceDao.deleteAll();
    }

    public List<Resource> getResourcesByProjectId(int projectId) {
        Specification<Resource> specification = queryResourceCriteria(projectId);
        return resourceDao.findAll(specification);
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

    public Optional<Project> addResourcesToProject(List<Resource> resourceList, int projectId) {
        Optional<Project> existProject = projectDao.findById(projectId);
        if (existProject.isPresent()) {
            Project project = existProject.get();
            for (Resource resource : resourceList) {
                project.addResource(resource);
            }
            resourceDao.saveAll(resourceList);
            projectDao.save(project);
        }
        return existProject;
    }

    public void deleteAllResourcesFromProject(Integer projectId) {
        Optional<Project> existProject = projectDao.findById(projectId);
        if (existProject.isPresent()) {
            Project project = existProject.get();
            List<Resource> resourceList = this.getResourcesByProjectId(projectId);
            for (Resource resource : resourceList) {
                project.removeResource(resource);
                resource.removeProject(project);
            }
            this.projectDao.save(project);
        }
    }
}
