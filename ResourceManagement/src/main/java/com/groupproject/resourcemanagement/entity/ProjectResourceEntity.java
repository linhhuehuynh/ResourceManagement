package com.groupproject.resourcemanagement.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "project_resource", schema = "mydb", catalog = "")
@IdClass(ProjectResourceEntityPK.class)
public class ProjectResourceEntity {
    private Integer projectId;
    private Integer resourceId;
    private ProjectEntity projectByProjectId;
    private ResourceEntity resourceByResourceId;

    @Id
    @Column(name = "project_id")
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Id
    @Column(name = "resource_id")
    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectResourceEntity that = (ProjectResourceEntity) o;
        return Objects.equals(projectId, that.projectId) &&
                Objects.equals(resourceId, that.resourceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, resourceId);
    }

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "project_id",insertable = false,updatable = false, nullable = false)
    public ProjectEntity getProjectByProjectId() {
        return projectByProjectId;
    }

    public void setProjectByProjectId(ProjectEntity projectByProjectId) {
        this.projectByProjectId = projectByProjectId;
    }

    @ManyToOne
    @JoinColumn(name = "resource_id", referencedColumnName = "resource_id",insertable = false,updatable = false, nullable = false)
    public ResourceEntity getResourceByResourceId() {
        return resourceByResourceId;
    }

    public void setResourceByResourceId(ResourceEntity resourceByResourceId) {
        this.resourceByResourceId = resourceByResourceId;
    }
}
