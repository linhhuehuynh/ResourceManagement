package com.example.demoresourcemanagement.entity;

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

//    @Column(name="project_id")
//    private int projectId;

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", foreignKey = @ForeignKey(name = "project_id_fk"), nullable = false, insertable = false, updatable = false)
    private Project project;

    public ProjectColumn() {
    }

    public ProjectColumn(String projectColumnName, String columnType, Project projectId) {
        this.projectColumnName = projectColumnName;
        this.columnType = columnType;
        this.project = projectId;
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

//    public int getProjectId() {
//        return projectId;
//    }
//
//    public void setProjectId(int projectId) {
//        this.projectId = projectId;
//    }

//    Commented out this to avoid infinite returning of json in Postman
    public int getProject() {
        return project.getId();
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
