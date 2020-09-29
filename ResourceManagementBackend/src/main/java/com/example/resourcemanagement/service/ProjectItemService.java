package com.example.resourcemanagement.service;

import com.example.resourcemanagement.dao.ProjectColumnDao;
import com.example.resourcemanagement.dao.ProjectItemDao;
import com.example.resourcemanagement.dao.ProjectRowDao;
import com.example.resourcemanagement.entity.ProjectColumn;
import com.example.resourcemanagement.entity.ProjectItem;
import com.example.resourcemanagement.entity.ProjectRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectItemService {

    @Autowired
    private ProjectItemDao projectItemDao;

    @Autowired
    private ProjectRowDao projectRowDao;

    @Autowired
    private ProjectColumnDao projectColumnDao;

    public Optional<List<ProjectItem>> getAllProjectItemListByProjectRowId(int rowId) {
        Optional<ProjectRow> projectRow = projectRowDao.findById(rowId);
        if(!projectRow.isPresent()) {
            return null;
        }
        return projectItemDao.getAllByProjectRowId(rowId);
    }

    public Optional<List<ProjectItem>> getAllProjectItemListByProjectColumnId(int colId) {
        Optional<ProjectColumn> projectColumn = projectColumnDao.findById(colId);
        if(!projectColumn.isPresent()) {
            return null;
        }
        return projectItemDao.getAllByProjectColumnId(colId);
    }

    public Optional<ProjectItem> getProjectItemByProjectRowAndColumnId(int rowId, int colId) {
        Optional<ProjectRow> projectRow = projectRowDao.findById(rowId);
        if(!projectRow.isPresent()) {
            return null;
        }
        Optional<ProjectColumn> projectColumn = projectColumnDao.findById(colId);
        if(!projectColumn.isPresent()) {
            return null;
        }
        return projectItemDao.getByProjectRowAndColumnId(rowId, colId);
    }

    public Optional<ProjectItem> getProjectItemById(int id) {
        return projectItemDao.findById(id);
    }

    public Optional<ProjectItem> createProjectItem(ProjectItem projectItem) {
        Optional<ProjectItem> existItem = this.getProjectItemByProjectRowAndColumnId(projectItem.getProjectRow().getId(), projectItem.getProjectColumn().getId());
        if (existItem == null) {
            return null;
        }
        if(existItem.isPresent()) {
            return Optional.empty();
        }
        ProjectItem createdItem = projectItemDao.save(projectItem);
        return Optional.of(createdItem);
    }

    public Optional<ProjectItem> updateProjectItem(ProjectItem projectItem, int id) {
        Optional<ProjectItem> item = projectItemDao.findById(id);
        if(item.isPresent()) {
            Optional<ProjectItem> existItem = this.getProjectItemByProjectRowAndColumnId(projectItem.getProjectRow().getId(), projectItem.getProjectColumn().getId());
            if (existItem == null) {
                return null;
            }
            if(existItem.isPresent()) {
                if(existItem.get().getId() != id) {
                    return null;
                }
            }
            projectItem.setId(id);
            ProjectItem savedItem = projectItemDao.save(projectItem);
            return Optional.of(savedItem);
        }
        return Optional.empty();
    }

    public Optional<ProjectItem> deleteProjectItemById(int id) {
        Optional<ProjectItem> item = projectItemDao.findById(id);
        if(item.isPresent()) {
            projectItemDao.deleteById(id);
            return Optional.of(item.get());
        }
        return Optional.empty();
    }
}
