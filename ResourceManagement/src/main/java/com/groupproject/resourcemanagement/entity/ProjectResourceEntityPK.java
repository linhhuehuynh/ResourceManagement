package com.groupproject.resourcemanagement.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ProjectResourceEntityPK implements Serializable {
    private Integer projectId;
    private Integer resourceId;

    @Column(name = "project_id")
    @Id
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Column(name = "resource_id")
    @Id
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
        ProjectResourceEntityPK that = (ProjectResourceEntityPK) o;
        return Objects.equals(projectId, that.projectId) &&
                Objects.equals(resourceId, that.resourceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, resourceId);
    }
}
