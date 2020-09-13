package com.groupproject.resourcemanagement.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "project_column", schema = "mydb", catalog = "")
public class ProjectColumn {
    private Integer projectColumnId;
    private Integer projectId;
    private String projectColumnName;
    private String columnType;
    private Project projectByProjectId;

    @Id
    @Column(name = "project_column_id")
    public Integer getProjectColumnId() {
        return projectColumnId;
    }

    public void setProjectColumnId(Integer projectColumnId) {
        this.projectColumnId = projectColumnId;
    }

    @Basic
    @Column(name = "project_id")
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "project_column_name")
    public String getProjectColumnName() {
        return projectColumnName;
    }

    public void setProjectColumnName(String projectColumnName) {
        this.projectColumnName = projectColumnName;
    }

    @Basic
    @Column(name = "column_type")
    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectColumn that = (ProjectColumn) o;
        return Objects.equals(projectColumnId, that.projectColumnId) &&
                Objects.equals(projectId, that.projectId) &&
                Objects.equals(projectColumnName, that.projectColumnName) &&
                Objects.equals(columnType, that.columnType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectColumnId, projectId, projectColumnName, columnType);
    }

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "project_id", nullable = false)
    public Project getProjectByProjectId() {
        return projectByProjectId;
    }

    public void setProjectByProjectId(Project projectByProjectId) {
        this.projectByProjectId = projectByProjectId;
    }
}
