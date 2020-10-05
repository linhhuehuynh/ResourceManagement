package com.example.resourcemanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resource_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "parent_resource_id")
    private int parentResourceId;

    @CreationTimestamp
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Column(name = "update_date")
    private Date updateDate;

//    @ManyToOne
//    @JsonIgnore
//    @JoinColumn(name = "parent_resource_id", insertable = false, updatable = false)
//    private Resource resourceByParentResourceId;
//    // remove "orphanremoval = true"
//    @OneToMany(mappedBy = "resourceByParentResourceId")
//    private List<Resource> resourcesByResourceId;

    @JsonIgnore
    @ManyToMany(mappedBy = "resourceList", fetch = FetchType.LAZY)
    private List<Project> projectList;


//    @OneToMany(mappedBy = "resource", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<ProjectResource> projectResource;

    public Resource() {
    }

    public void addProject(Project project) {
        this.projectList.add(project);
    }

    public void removeProject(Project project) {
        this.projectList.remove(project);
    }

    public Resource(String name, String code, int parentResourceId) {
        this.name = name;
        this.code = code;
        this.parentResourceId = parentResourceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getParentResourceId() {
        return parentResourceId;
    }

    public void setParentResourceId(int parentResourceId) {
        this.parentResourceId = parentResourceId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }


    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    //    public int getParentResourceId() {
//        return parentResourceId;
//    }
//
//    public void setParentResourceId(int parentResourceId) {
//        this.parentResourceId = parentResourceId;
//    }

//    @OneToMany(mappedBy = "resource", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    public List<ProjectResource> getProjectResource() {
//        return projectResource;
//    }
//
//    public void setProjectResource(List<ProjectResource> projectResource) {
//        this.projectResource = projectResource;
//    }

//    @ManyToOne
//    @JsonIgnore
//    @JoinColumn(name = "parent_resource_id", foreignKey = @ForeignKey(name = "parent_resource_id_fk"))
//    public Resource getResourceByParentResourceId() {
//        return resourceByParentResourceId;
//    }
//
//    public void setResourceByParentResourceId(Resource resourceByParentResourceId) {
//        this.resourceByParentResourceId = resourceByParentResourceId;
//    }



    //Commented out to avoid infinite return of JSON objects in Postman
//    @OneToMany(mappedBy = "resourceByParentResourceId")
//    public List<Resource> getResourcesByResourceId() {
//        return resourcesByResourceId;
//    }
//
//    public void setResourcesByResourceId(List<Resource> resourcesByResourceId) {
//        this.resourcesByResourceId = resourcesByResourceId;
//    }
}
