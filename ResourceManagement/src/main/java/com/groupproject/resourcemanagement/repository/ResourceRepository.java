package com.groupproject.resourcemanagement.repository;


import com.groupproject.resourcemanagement.entity.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;



/**
 *
 *
 * Public void addResource(Resource resource) {}
 * Public void addResourceList(List<Resource> resourceList)
 * * Public List<Resource> getResourceAll() {}
 * public List<Resource> getResourcesAllByProjectId(int projectId)
 * public boolean addResourceListToProject(List<Resource> resources)
 * public boolean removeResourceListFromProject(projectId, List<Resource> resource)
 *public List<Resource> getAllResourcesByProjectId（int projectId）
 */


public interface ResourceRepository extends JpaRepository<ResourceEntity, Integer> {

}


