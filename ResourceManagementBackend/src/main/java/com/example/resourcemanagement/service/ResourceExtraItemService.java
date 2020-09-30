package com.example.resourcemanagement.service;


import com.example.resourcemanagement.entity.ResourceExtraItem;
import com.example.resourcemanagement.dao.ResourceColumnDao;
import com.example.resourcemanagement.dao.ResourceExtraItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ResourceExtraItemService {
    @Autowired
    private ResourceExtraItemDao resourceExtraItemDao;

    @Autowired
    private ResourceColumnDao resourceColumnDao;

    public Optional<ResourceExtraItem> getResourceExtraItemById(int id) {
        Optional<ResourceExtraItem> resourceExtraItem = resourceExtraItemDao.findById(id);
        if (resourceExtraItem.isPresent()) {
            return resourceExtraItem;
        }
        return Optional.empty();
    }

    public Optional<ResourceExtraItem> createResourceExtraItem(ResourceExtraItem resourceExtraItem) {
        ResourceExtraItem createdResourceExtraItem = resourceExtraItemDao.save(resourceExtraItem);
        Optional<ResourceExtraItem> result = Optional.of(createdResourceExtraItem);
        if (result.isPresent()) {
            return result;
        }
        return Optional.empty();
    }

    public Optional<ResourceExtraItem> updateResourceExtraItem(ResourceExtraItem resourceExtraItem, int id) {
        Optional<ResourceExtraItem> column = resourceExtraItemDao.findById(id);
        if (column.isPresent()) {
            resourceExtraItem.setId(id);
            ResourceExtraItem savedProjectColumn = resourceExtraItemDao.save(resourceExtraItem);
            Optional<ResourceExtraItem> result = Optional.of(savedProjectColumn);
            return result;
        }
        return Optional.empty();

    }

    public Optional<ResourceExtraItem> deleteResourceExtraItemById(int id) {
        Optional<ResourceExtraItem> column = resourceExtraItemDao.findById(id);
        if (column.isPresent()) {
            resourceExtraItemDao.deleteById(id);
            return Optional.of(column.get());
        }
        return Optional.empty();
    }
}
