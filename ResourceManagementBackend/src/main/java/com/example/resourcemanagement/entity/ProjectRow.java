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
public class ProjectRow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="project_row_id")
    private int id;

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Project project;

    @OneToMany(mappedBy = "projectRow", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProjectItem> projectItemList;

    public ProjectRow() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonBackReference
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

//    @JsonManagedReference
//    public List<ProjectItem> getProjectItemList() {
//        return projectItemList;
//    }
//
//    public void setProjectItemList(List<ProjectItem> projectItemList) {
//        this.projectItemList = projectItemList;
//    }
}
