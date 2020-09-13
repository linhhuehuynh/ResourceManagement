package com.groupproject.resourcemanagement.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;

@Entity
@Table(name = "project", schema = "mydb", catalog = "")
public class Project {
    private Integer projectId;
    private Integer userId;
    private String name;
    private Date createDate;
    private Date updateDate;
    private User userByUserId;
    private List<ProjectColumn> projectColumnsByProjectId;
    private List<ProjectResource> projectResourcesByProjectId;

    @Id
    @Column(name = "project_id")
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "update_date")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project that = (Project) o;
        return Objects.equals(projectId, that.projectId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(updateDate, that.updateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, userId, name, createDate, updateDate);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @OneToMany(mappedBy = "projectByProjectId")
    public List<ProjectColumn> getProjectColumnsByProjectId() {
        return projectColumnsByProjectId;
    }

    public void setProjectColumnsByProjectId(List<ProjectColumn> projectColumnsByProjectId) {
        this.projectColumnsByProjectId = projectColumnsByProjectId;
    }

    @OneToMany(mappedBy = "projectByProjectId")
    public List<ProjectResource> getProjectResourcesByProjectId() {
        return projectResourcesByProjectId;
    }

    public void setProjectResourcesByProjectId(List<ProjectResource> projectResourcesByProjectId) {
        this.projectResourcesByProjectId = projectResourcesByProjectId;
    }
}
