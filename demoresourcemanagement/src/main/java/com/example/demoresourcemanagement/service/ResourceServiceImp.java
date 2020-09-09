package com.example.demoresourcemanagement.service;


import com.example.demoresourcemanagement.dao.ResourceDao;
import com.example.demoresourcemanagement.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ResourceServiceImp implements ResourceService {

    @Autowired
    ResourceDao resourceDao;

    @Override
    public void addResource(Resource resource) {
        resourceDao.save(resource);
    }

    @Override
    public void addResourceList(List<Resource> resourceList) {
        resourceDao.saveAll(resourceList);
    }

    @Override
    public List<Resource> getAllResources() {
        return resourceDao.findAll();
    }

    @Override
    public Optional<Resource> getResourceById(int resourceId) {
        return resourceDao.findById(resourceId);
    }

    @Override
    public Resource getResourceByCreateDate(Date createDate) {
        return resourceDao.findByCreateDate(createDate);
    }

    @Override
    public void setResourceById(Resource resource) {
        resourceDao.save(resource);
    }

    @Override
    public void deleteResourceById(int resourceId) {
        resourceDao.deleteById(resourceId);
    }

    @Override
    public void deleteAllResources() {
        resourceDao.deleteAll();

    }

}
