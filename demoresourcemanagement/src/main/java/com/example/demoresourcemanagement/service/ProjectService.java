package com.example.demoresourcemanagement.service;


import com.example.demoresourcemanagement.entity.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {


    Project addProjet(Project project);

    //List<Project> saveAll(List<Project> projects);

    Optional<Project> findById(int id);

    // Optional<Project> findByName(String name);

    List<Project> findAll();

    void delete(int id);

    void deleteAll();

    // void delete(Project project);
}
