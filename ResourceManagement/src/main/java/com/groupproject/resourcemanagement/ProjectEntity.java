package com.groupproject.resourcemanagement;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "project", schema = "mydb", catalog = "")
public class ProjectEntity {
    private Integer projectId;
    private Integer userId;
    private String name;
    private Date createDate;
    private Date updateDate;
    private UserEntity userByUserId;
    private Collection<ProjectColumnEntity> projectColumnsByProjectId;
    private Collection<ProjectResourceEntity> projectResourcesByProjectId;

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
        ProjectEntity that = (ProjectEntity) o;
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
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    @OneToMany(mappedBy = "projectByProjectId")
    public Collection<ProjectColumnEntity> getProjectColumnsByProjectId() {
        return projectColumnsByProjectId;
    }

    public void setProjectColumnsByProjectId(Collection<ProjectColumnEntity> projectColumnsByProjectId) {
        this.projectColumnsByProjectId = projectColumnsByProjectId;
    }

    @OneToMany(mappedBy = "projectByProjectId")
    public Collection<ProjectResourceEntity> getProjectResourcesByProjectId() {
        return projectResourcesByProjectId;
    }

    public void setProjectResourcesByProjectId(Collection<ProjectResourceEntity> projectResourcesByProjectId) {
        this.projectResourcesByProjectId = projectResourcesByProjectId;
    }
}
