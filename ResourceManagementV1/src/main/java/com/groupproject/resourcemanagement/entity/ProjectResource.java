package com.groupproject.resourcemanagement.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "project_resource", schema = "mydb", catalog = "")
@IdClass(ProjectResourceEntityPK.class)
public class ProjectResource {
    private Integer projectId;
    private Integer resourceId;
    private Project projectByProjectId;
    private Resource resourceByResourceId;

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
        ProjectResource that = (ProjectResource) o;
        return Objects.equals(projectId, that.projectId) &&
                Objects.equals(resourceId, that.resourceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, resourceId);
    }

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "project_id", nullable = false)
    public Project getProjectByProjectId() {
        return projectByProjectId;
    }

    public void setProjectByProjectId(Project projectByProjectId) {
        this.projectByProjectId = projectByProjectId;
    }

    @ManyToOne
    @JoinColumn(name = "resource_id", referencedColumnName = "resource_id", nullable = false)
    public Resource getResourceByResourceId() {
        return resourceByResourceId;
    }

    public void setResourceByResourceId(Resource resourceByResourceId) {
        this.resourceByResourceId = resourceByResourceId;
    }
}
