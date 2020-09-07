package com.groupproject.resourcemanagement.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "resource", schema = "mydb", catalog = "")
public class ResourceEntity {
    private Integer resourceId;
    private String name;
    private String code;
    private Date createDate;
    private Date updateDate;
    private Integer parentResourceId;
    private Collection<ProjectResourceEntity> projectResourcesByResourceId;
    private ResourceEntity resourceByParentResourceId;
    private Collection<ResourceEntity> resourcesByResourceId;

    @Id
    @Column(name = "resource_id")
    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
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
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    @Basic
    @Column(name = "parent_resource_id")
    public Integer getParentResourceId() {
        return parentResourceId;
    }

    public void setParentResourceId(Integer parentResourceId) {
        this.parentResourceId = parentResourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourceEntity that = (ResourceEntity) o;
        return Objects.equals(resourceId, that.resourceId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(code, that.code) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(parentResourceId, that.parentResourceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceId, name, code, createDate, updateDate, parentResourceId);
    }

    @OneToMany(mappedBy = "resourceByResourceId")
    public Collection<ProjectResourceEntity> getProjectResourcesByResourceId() {
        return projectResourcesByResourceId;
    }

    public void setProjectResourcesByResourceId(Collection<ProjectResourceEntity> projectResourcesByResourceId) {
        this.projectResourcesByResourceId = projectResourcesByResourceId;
    }

    @ManyToOne
    @JoinColumn(name = "parent_resource_id", referencedColumnName = "resource_id")
    public ResourceEntity getResourceByParentResourceId() {
        return resourceByParentResourceId;
    }

    public void setResourceByParentResourceId(ResourceEntity resourceByParentResourceId) {
        this.resourceByParentResourceId = resourceByParentResourceId;
    }

    @OneToMany(mappedBy = "resourceByParentResourceId")
    public Collection<ResourceEntity> getResourcesByResourceId() {
        return resourcesByResourceId;
    }

    public void setResourcesByResourceId(Collection<ResourceEntity> resourcesByResourceId) {
        this.resourcesByResourceId = resourcesByResourceId;
    }
}
