package com.example.demoresourcemanagement.service;

import com.example.demoresourcemanagement.dao.ResourceDao;
import com.example.demoresourcemanagement.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    public List<Resource> getAllResources() {
        return resourceDao.findAll();
    }

    public Resource addResource(Resource resource){
        return resourceDao.save(resource);
    }

    public void deleteResourceById(int id){
        resourceDao.deleteById(id);
    }

    public void deleteAllResources(){
        resourceDao.deleteAll();
    }

    public Resource updateResource(Resource resource){
        return resourceDao.save(resource);
    }
}