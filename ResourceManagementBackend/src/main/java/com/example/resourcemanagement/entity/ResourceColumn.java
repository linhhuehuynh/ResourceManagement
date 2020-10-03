package com.example.resourcemanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ResourceColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="resource_column_id")
    private int id;

    @Column(name="resource_column_name")
    private String resourceColumnName;

    @OneToMany(mappedBy = "resourceColumn", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ResourceExtraItem> resourceExtraItemList;

    public ResourceColumn() {
    }

    public ResourceColumn(String resourceColumnName) {
        this.resourceColumnName = resourceColumnName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResourceColumnName() {
        return resourceColumnName;
    }

    public void setResourceColumnName(String resourceColumnName) {
        this.resourceColumnName = resourceColumnName;
    }

//    @JsonManagedReference
//    public List<ResourceExtraItem> getResourceExtraItems() {
//        return resourceExtraItemList;
//    }
//
//    public void setResourceExtraItems(List<ResourceExtraItem> resourceExtraItemList) {
//        this.resourceExtraItemList = resourceExtraItemList;
//    }

}
