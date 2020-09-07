package com.groupproject.resourcemanagement.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ProjectResourceEntityPK implements Serializable {
    private int projectId;
    private int resourceId;

    @Column(name = "project_id")
    @Id
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Column(name = "resource_id")
    @Id
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

        ProjectResourceEntityPK that = (ProjectResourceEntityPK) o;

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
