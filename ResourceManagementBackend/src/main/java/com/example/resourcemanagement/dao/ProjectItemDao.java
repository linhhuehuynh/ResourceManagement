package com.example.resourcemanagement.dao;

import com.example.resourcemanagement.entity.ProjectItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProjectItemDao extends JpaRepository<ProjectItem, Integer> {

    @Query("select p from ProjectItem p where p.projectRow.id = ?1")
    Optional<List<ProjectItem>> getAllByProjectRowId(int rowId);

    @Query("select p from ProjectItem p where p.projectColumn.id = ?1")
    Optional<List<ProjectItem>> getAllByProjectColumnId(int colId);

    @Query("select p from ProjectItem p where p.projectRow.id = ?1 and p.projectColumn.id = ?2")
    Optional<ProjectItem> getByProjectRowAndColumnId(int rowId, int colId);
}
