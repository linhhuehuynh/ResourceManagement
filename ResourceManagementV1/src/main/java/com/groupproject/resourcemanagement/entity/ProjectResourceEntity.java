package com.groupproject.resourcemanagement.entity;

import javax.persistence.*;

@Entity
@Table(name = "project_resource", schema = "mydb", catalog = "")
@IdClass(ProjectResourceEntityPK.class)
public class ProjectResourceEntity {
    private int projectId;
    private int resourceId;

    @Id
    @Column(name = "project_id")
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Id
    @Column(name = "resource_id")
    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectResourceEntity that = (ProjectResourceEntity) o;

        if (projectId != that.projectId) return false;
        if (resourceId != that.resourceId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = projectId;
        result = 31 * result + resourceId;
        return result;
    }
}
