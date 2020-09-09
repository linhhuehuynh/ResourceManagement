package com.example.demoresourcemanagement.service;

import com.example.demoresourcemanagement.entity.Resource;

import java.util.*;

public interface ResourceService {

    void addResource(Resource resource);

    void addResourceList(List<Resource> resourceList);

    List<Resource> getAllResources(); // 不需要传参数

    Resource getResourceById(int resource_id);

    Resource getResourceByCreateDate(Date createDate);

    void setResourceById(Resource resource);

    void deleteResourceById(int resourceId);

    void deleteAllResources();

}
