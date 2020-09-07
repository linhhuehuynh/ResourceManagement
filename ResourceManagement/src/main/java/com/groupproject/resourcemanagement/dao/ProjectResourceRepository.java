package com.groupproject.resourcemanagement.dao;

import com.groupproject.resourcemanagement.entity.ProjectResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface ProjectResourceRepository extends JpaRepository<ProjectResource, Integer> {
    List<ProjectResource> findByProjectId(Integer projectId);
    boolean existsByProjectId(Integer projectId);
}
