package com.example.resourcemanagement.entity;

import com.fasterxml.jackson.annotation.*;
import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private int id;

    @Column(name = "name", unique = true)
    @NotNull
    private String name;

    @CreationTimestamp
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Column(name = "update_date")
    private Date updateDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//    @JsonIdentityReference(alwaysAsId = true)
//    @JsonProperty("userId")
    private User user;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProjectColumn> projectColumns;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProjectRow> projectRows;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "project_resource",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id")
    )
    private List<Resource> resourceList;

    public Project() {
    }

    public Project(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public void addResource(Resource resource) {
        this.resourceList.add(resource);
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

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }

    @JsonBackReference
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    @JsonProperty("userId")
//    public void setUserById(int userId) {
//        user = User.fromId(userId);
//    }

    @JsonManagedReference
    public List<ProjectColumn> getProjectColumns() {
        return projectColumns;
    }

    public void setProjectColumns(List<ProjectColumn> projectColumns) {
        this.projectColumns = projectColumns;
    }

    public List<ProjectRow> getProjectRows() {
        return projectRows;
    }

    @JsonManagedReference
    public void setProjectRows(List<ProjectRow> projectRows) {
        this.projectRows = projectRows;
    }
}



