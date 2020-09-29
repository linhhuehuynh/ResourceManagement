package com.example.resourcemanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProjectItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="project_item_id")
    private int id;

    @Column(name="value")
    private String value;

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_row_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProjectRow projectRow;

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_column_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProjectColumn projectColumn;

//    @Column(name="project_column_id")
//    private String projectColumn;

    public ProjectItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

//    @JsonBackReference
    public ProjectRow getProjectRow() {
        return projectRow;
    }

    public void setProjectRow(ProjectRow projectRow) {
        this.projectRow = projectRow;
    }

//    @JsonBackReference
    public ProjectColumn getProjectColumn() {
        return projectColumn;
    }

    public void setProjectColumn(ProjectColumn projectColumn) {
        this.projectColumn = projectColumn;
    }
}
