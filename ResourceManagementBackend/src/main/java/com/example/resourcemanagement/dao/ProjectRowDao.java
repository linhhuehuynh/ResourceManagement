package com.example.resourcemanagement.dao;

import com.example.resourcemanagement.entity.ProjectRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProjectRowDao extends JpaRepository<ProjectRow, Integer> {

    @Query("select p from ProjectRow p where p.project.id = ?1")
    Optional<List<ProjectRow>> getAllByProjectId(int projectId);
}
