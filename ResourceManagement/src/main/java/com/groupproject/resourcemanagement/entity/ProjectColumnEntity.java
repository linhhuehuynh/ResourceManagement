package com.groupproject.resourcemanagement.entity;

import javax.persistence.*;

@Entity
@Table(name = "project_column", schema = "mydb", catalog = "")
public class ProjectColumnEntity {
    private int projectColumnId;
    private int projectId;
    private String projectColumnName;
    private String columnType;

    @Id
    @Column(name = "project_column_id")
    public int getProjectColumnId() {
        return projectColumnId;
    }

    public void setProjectColumnId(int projectColumnId) {
        this.projectColumnId = projectColumnId;
    }

    @Basic
    @Column(name = "project_id")
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
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

        ProjectColumnEntity that = (ProjectColumnEntity) o;

        if (projectColumnId != that.projectColumnId) return false;
        if (projectId != that.projectId) return false;
        if (projectColumnName != null ? !projectColumnName.equals(that.projectColumnName) : that.projectColumnName != null)
            return false;
        if (columnType != null ? !columnType.equals(that.columnType) : that.columnType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = projectColumnId;
        result = 31 * result + projectId;
        result = 31 * result + (projectColumnName != null ? projectColumnName.hashCode() : 0);
        result = 31 * result + (columnType != null ? columnType.hashCode() : 0);
        return result;
    }
}
