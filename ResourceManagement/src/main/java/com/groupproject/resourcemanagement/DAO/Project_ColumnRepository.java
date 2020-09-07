package com.groupproject.resourcemanagement.DAO;

import com.groupproject.resourcemanagement.entity.ProjectColumnEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Project_ColumnRepository extends JpaRepository<ProjectColumnEntity,Integer> {
    ProjectColumnEntity findByProjectColumnId(Integer project_column_id);

    List<ProjectColumnEntity> findAllByProjectId(Integer project_id);

    ProjectColumnEntity createByProjectColumn(ProjectColumnEntity projectColumnEntity);

    boolean updateByProjectColumnId(ProjectColumnEntity projectColumnEntity, Integer project_column_id);

    boolean removeByProjectColumnId(Integer project_column_id);
}
