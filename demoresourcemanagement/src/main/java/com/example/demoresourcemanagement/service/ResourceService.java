package com.example.demoresourcemanagement.service;

import com.example.demoresourcemanagement.entity.Resource;

import java.util.Date;
import java.util.List;

/**
 * C:
 * Public Resource CreateResource(Str name, Str code,int ParentId)
 * Public List<Resource> CreateResources(List<Resource> Resources)?
 * R:
 * Public List<Resource> GetAllResources()
 * Public Resource GetResourceById(int resource_id)
 * Public Resource GetResourceByCreate_date(Date create_date)
 * Public List<Resource> GetResourcesByParentId(int ParentId)
 * U:
 * Public void setResourceById(Resource resource)
 * D:
 * Public Resource RemoveResourceById(int resource_id)
 * Public Resource RemoveResourceByName(Str name)
 * Public Resource RemoveResourceByCode(Str Code)
 * Public Resource RemoveResourceByCreate_date(Date create_date)
 * Public List<Resource> RemoveResourcesByParentId(int ParentId)
 * Public List<Resource> RemoveAllResources()
 */

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