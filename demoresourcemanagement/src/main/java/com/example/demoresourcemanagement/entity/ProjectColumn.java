package com.example.demoresourcemanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProjectColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="project_column_id")
    private int id;

    @Column(name="project_column_name")
    private String projectColumnName;

    @Column(name="column_type")
    private String columnType;

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", foreignKey = @ForeignKey(name = "project_id_fk"), nullable = false)
    private Project project;

    public ProjectColumn() {
    }

    public ProjectColumn(String projectColumnName, String columnType, Project project) {
        this.projectColumnName = projectColumnName;
        this.columnType = columnType;
        this.project = project;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectColumnName() {
        return projectColumnName;
    }

    public void setProjectColumnName(String projectColumnName) {
        this.projectColumnName = projectColumnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    @JsonBackReference
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
