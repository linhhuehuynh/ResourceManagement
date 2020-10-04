package com.example.resourcemanagement.dao;

import com.example.resourcemanagement.entity.ProjectColumn;
import com.example.resourcemanagement.entity.ProjectItem;
import com.example.resourcemanagement.entity.ResourceExtraItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ResourceExtraItemDao extends JpaRepository<ResourceExtraItem, Integer> {

   Optional<List<ResourceExtraItem>> getAllByResourceColumnId(int resourceColumnId);

   Optional<List<ResourceExtraItem>> getAllByResourceId(int resourceId);

   @Query("select r from ResourceExtraItem r where r.resource.id = ?1 and r.resourceColumn.id = ?2")
   Optional<ResourceExtraItem> getResourceExtraItemByResourceAndResourceColumn(int resourceId, int resourceColumnId);

//   @Query("select p from ProjectItem p where p.projectRow.id = ?1")
//   Optional<List<ProjectItem>> getAllByProjectRowId(int rowId);
//
//   @Query("select p from ProjectItem p where p.projectColumn.id = ?1")
//   Optional<List<ProjectItem>> getAllByProjectColumnId(int colId);
//
//   @Query("select p from ProjectItem p where p.projectRow.id = ?1 and p.projectColumn.id = ?2")
//   Optional<ProjectItem> getByProjectRowAndColumnId(int rowId, int colId);


}