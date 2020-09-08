package com.example.demoresourcemanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name ="resource_id")
    private int id;

    @Column (name="name")
    private String name;

    @Column (name="code")
    private String code;

    @Column (name="create_date")
    private Date createDate;

    @Column (name="update_date")
    private Date updateDate;

//    @Column(name = "parent_resource_id")
//    private int parentResourceId;

    @ManyToOne
    @JoinColumn(name = "parent_resource_id",  foreignKey = @ForeignKey(name = "parent_resource_id_fk"))
    private Resource resourceByParentResourceId;

    @OneToMany(mappedBy = "resourceByParentResourceId")
    private List<Resource> resourcesByResourceId;


//    @OneToMany(mappedBy = "resource", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<ProjectResource> projectResource;

    public Resource() {
    }

    public Resource(int id, String name, String code, Date createDate, Date updateDate) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.createDate = createDate;
        this.updateDate = updateDate;
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

    @ManyToOne
    @JoinColumn(name = "parent_resource_id",  foreignKey = @ForeignKey(name = "parent_resource_id_fk"))
    public Resource getResourceByParentResourceId() {
        return resourceByParentResourceId;
    }

    public void setResourceByParentResourceId(Resource resourceByParentResourceId) {
        this.resourceByParentResourceId = resourceByParentResourceId;
    }

    @OneToMany(mappedBy = "resourceByParentResourceId")
    public List<Resource> getResourcesByResourceId() {
        return resourcesByResourceId;
    }

    public void setResourcesByResourceId(List<Resource> resourcesByResourceId) {
        this.resourcesByResourceId = resourcesByResourceId;
    }
}
