package com.groupproject.resourcemanagement.service;

import com.groupproject.resourcemanagement.entity.ProjectColumnEntity;

import java.util.List;

public interface Project_ColumnService {
    ProjectColumnEntity create(ProjectColumnEntity projectColumnEntity);

    ProjectColumnEntity findOne(Integer project_column_id);

    List<ProjectColumnEntity> findList(Integer project_id);

    boolean update(ProjectColumnEntity projectColumnEntity, Integer project_column_id);

    boolean delete(Integer project_column_id);
}
