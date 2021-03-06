package com.example.resourcemanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

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
    @JoinColumn(name = "project_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Project project;

    @OneToMany(mappedBy = "projectColumn", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProjectItem> projectItemList;

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


//    public List<ProjectItem> getProjectItemList() {
//        return projectItemList;
//    }
//
//    public void setProjectItemList(List<ProjectItem> projectItemList) {
//        this.projectItemList = projectItemList;
//    }
}
