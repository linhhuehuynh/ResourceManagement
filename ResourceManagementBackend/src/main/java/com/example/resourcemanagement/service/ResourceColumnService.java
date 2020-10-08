package com.example.resourcemanagement.service;


import com.example.resourcemanagement.dao.ResourceColumnDao;
import com.example.resourcemanagement.entity.ResourceColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceColumnService {

    @Autowired
    private ResourceColumnDao resourceColumnDao;

    public Optional<ResourceColumn> getColumnNameById(int id) {
        Optional<ResourceColumn> resourceColumn = resourceColumnDao.findById(id);
        if (resourceColumn.isPresent()) {
            return resourceColumn;
        }
        return Optional.empty();
    }

    public Optional<List<ResourceColumn>> getAllResourceColumnName() {
        List<ResourceColumn> resourceColumnList = resourceColumnDao.findAll();
        if (resourceColumnList.size() != 0) {
            return Optional.of(resourceColumnList);
        }
        return Optional.empty();
    }

    public Optional<ResourceColumn> createColumnName(ResourceColumn resourceColumn) {
        ResourceColumn createdResourceColumn = resourceColumnDao.save(resourceColumn);
        Optional<ResourceColumn> result = Optional.of(createdResourceColumn);
        if (result.isPresent()) {
            return result;
        }
        return Optional.empty();
    }

    public Optional<ResourceColumn> updateColumnName(ResourceColumn resourceColumn, int id) {
        Optional<ResourceColumn> column = resourceColumnDao.findById(id);
        if (column.isPresent()) {
            resourceColumn.setId(id);
            ResourceColumn savedProjectColumn = resourceColumnDao.save(resourceColumn);
            Optional<ResourceColumn> result = Optional.of(savedProjectColumn);
            return result;
        }
        return Optional.empty();

    }

    public Optional<ResourceColumn> deleteColumnNameById(int id) {
        Optional<ResourceColumn> column = resourceColumnDao.findById(id);
        if (column.isPresent()) {
            resourceColumnDao.deleteById(id);
            return Optional.of(column.get());
        }
        return Optional.empty();
    }

    public void deleteAllColumns() { resourceColumnDao.deleteAll();
    }

}
