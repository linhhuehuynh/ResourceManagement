package com.example.SecurityDemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class SysRole implements Serializable {
    static final long serialVersionUID = 1L;

    @Id
    @Column(name="role_id")
    private int id;

    @Column(name="name")
    private String name;

    public SysRole() {

    }

    public SysRole(int id, String name) {
        this.id = id;
        this.name = name;
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

    //database link
}
