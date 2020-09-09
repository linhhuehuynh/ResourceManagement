package com.example.demoresourcemanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SysUserRole implements Serializable {
    static final long serialVersionUID = 1L;

    @Column(name="user_id")
    private int user_id;

    @Column(name="role_id")
    private int role_id;

    public SysUserRole(int user_id, int role_id) {
        this.user_id = user_id;
        this.role_id = role_id;
    }

    public SysUserRole() {

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }
}
