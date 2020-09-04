package com.groupproject.resourcemanagement;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "mydb", catalog = "")
public class UserEntity {
    private Integer userId;
    private String profilePic;
    private Date createDate;
    private Date updateDate;
    private String role;
    private Collection<ProjectEntity> projectsByUserId;
    private UserCredentialEntity userCredentialByUserId;

    @Id
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "profile_pic")
    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
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
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(profilePic, that.profilePic) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, profilePic, createDate, updateDate, role);
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<ProjectEntity> getProjectsByUserId() {
        return projectsByUserId;
    }

    public void setProjectsByUserId(Collection<ProjectEntity> projectsByUserId) {
        this.projectsByUserId = projectsByUserId;
    }

    @OneToOne(mappedBy = "userByUserId")
    public UserCredentialEntity getUserCredentialByUserId() {
        return userCredentialByUserId;
    }

    public void setUserCredentialByUserId(UserCredentialEntity userCredentialByUserId) {
        this.userCredentialByUserId = userCredentialByUserId;
    }
}
