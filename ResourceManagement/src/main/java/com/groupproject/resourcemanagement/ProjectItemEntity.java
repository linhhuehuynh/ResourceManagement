package com.groupproject.resourcemanagement;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "project_item", schema = "mydb", catalog = "")
public class ProjectItemEntity {
    private Integer projectItemId;
    private Integer projectRowId;
    private Integer projectColumnId;
    private String value;
    private String typeName;
    private ProjectColumnEntity projectColumnByProjectColumnId;

    @Id
    @Column(name = "project_item_id")
    public Integer getProjectItemId() {
        return projectItemId;
    }

    public void setProjectItemId(Integer projectItemId) {
        this.projectItemId = projectItemId;
    }

    @Basic
    @Column(name = "project_row_id")
    public Integer getProjectRowId() {
        return projectRowId;
    }

    public void setProjectRowId(Integer projectRowId) {
        this.projectRowId = projectRowId;
    }

    @Basic
    @Column(name = "project_column_id")
    public Integer getProjectColumnId() {
        return projectColumnId;
    }

    public void setProjectColumnId(Integer projectColumnId) {
        this.projectColumnId = projectColumnId;
    }

    @Basic
    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "type_name")
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectItemEntity that = (ProjectItemEntity) o;
        return Objects.equals(projectItemId, that.projectItemId) &&
                Objects.equals(projectRowId, that.projectRowId) &&
                Objects.equals(projectColumnId, that.projectColumnId) &&
                Objects.equals(value, that.value) &&
                Objects.equals(typeName, that.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectItemId, projectRowId, projectColumnId, value, typeName);
    }

    @ManyToOne
    @JoinColumn(name = "project_column_id", referencedColumnName = "project_column_id", nullable = false)
    public ProjectColumnEntity getProjectColumnByProjectColumnId() {
        return projectColumnByProjectColumnId;
    }

    public void setProjectColumnByProjectColumnId(ProjectColumnEntity projectColumnByProjectColumnId) {
        this.projectColumnByProjectColumnId = projectColumnByProjectColumnId;
    }
}
