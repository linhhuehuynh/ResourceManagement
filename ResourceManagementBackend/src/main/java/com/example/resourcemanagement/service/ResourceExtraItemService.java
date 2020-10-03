package com.example.resourcemanagement.service;


import com.example.resourcemanagement.dao.ResourceColumnDao;
import com.example.resourcemanagement.dao.ResourceDao;
import com.example.resourcemanagement.dao.ResourceExtraItemDao;
import com.example.resourcemanagement.entity.Resource;
import com.example.resourcemanagement.entity.ResourceColumn;
import com.example.resourcemanagement.entity.ResourceExtraItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ResourceExtraItemService {
    @Autowired
    private ResourceExtraItemDao resourceExtraItemDao;

    @Autowired
    private ResourceColumnDao resourceColumnDao;

    @Autowired
    private ResourceDao resourceDao;

    public Optional<List<ResourceExtraItem>> getResourceExtraItemListByResourceColumnId(int resourceColumnId) {
        Optional<ResourceColumn> resourceExtraItemColumn = resourceColumnDao.findById(resourceColumnId);
        if (!resourceExtraItemColumn.isPresent()) {
            return Optional.empty();
        }
        return resourceExtraItemDao.getAllByResourceColumnId(resourceColumnId);
    }

    public Optional<List<ResourceExtraItem>> getResourceExtraItemListByResourceId(int resourceId) {
        Optional<Resource> resource = resourceDao.findById(resourceId);
        if (!resource.isPresent()) {
            return Optional.empty();
        }
        return resourceExtraItemDao.getAllByResourceId(resourceId);
    }

    public Optional<ResourceExtraItem> getResourceExtraItemByResourceAndColmunId(int resourceId, int resourceColumnId) {
        Optional<Resource> resource = resourceDao.findById(resourceId);
        if (!resource.isPresent()) {
            return Optional.empty();
        }
        Optional<ResourceColumn> resourceExtraItemColumn = resourceColumnDao.findById(resourceColumnId);
        if (!resourceExtraItemColumn.isPresent()) {
            return Optional.empty();
        }
        return resourceExtraItemDao.getResourceExtraItemByResourceAndResourceColumn(resourceId, resourceColumnId);
    }

    public Optional<ResourceExtraItem> getResourceExtraItemById(int id) {
        Optional<ResourceExtraItem> resourceExtraItem = resourceExtraItemDao.findById(id);
        if (resourceExtraItem.isPresent()) {
            return resourceExtraItem;
        }
        return Optional.empty();
    }

    public Optional<ResourceExtraItem> createResourceExtraItem(ResourceExtraItem resourceExtraItem) {
        Optional<ResourceExtraItem> existItem = this.getResourceExtraItemByResourceAndColmunId(
                resourceExtraItem.getResource().getId(), resourceExtraItem.getResourceColumn().getId());
        if (existItem.isPresent()) {
            return Optional.empty();
        }
        ResourceExtraItem createdItem = resourceExtraItemDao.save(resourceExtraItem);
        return Optional.of(createdItem);
    }

    public Optional<ResourceExtraItem> updateResourceExtraItem(ResourceExtraItem resourceExtraItem, int id) {
        Optional<ResourceExtraItem> column = resourceExtraItemDao.findById(id);
        if (column.isPresent()) {
            resourceExtraItem.setId(id);
            ResourceExtraItem savedProjectColumn = resourceExtraItemDao.save(resourceExtraItem);
            return Optional.of(savedProjectColumn);
        }
        return column;
    }

    public Optional<ResourceExtraItem> deleteResourceExtraItemById(int id) {
        Optional<ResourceExtraItem> column = resourceExtraItemDao.findById(id);
        if (column.isPresent()) {
            resourceExtraItemDao.deleteById(id);
            return Optional.of(column.get());
        }
        return column;
    }

//    public Optional<List<ResourceExtraItem>> getAllResourceExtraItemsByResourceColumnId(int id){
//        Optional<List<ResourceExtraItem>> extraItems = resourceExtraItemDao.getAllByResourceColumnId(id);
//        if (extraItems.isPresent()) {
//            return extraItems;
//        }
//        return Optional.empty();
//    }
}
