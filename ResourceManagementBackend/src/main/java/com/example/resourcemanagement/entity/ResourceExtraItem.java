package com.example.resourcemanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ResourceExtraItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="resource_extra_item_id")
    private int id;

    @Column(name="value")
    private String resourceExtraItemValue;

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name = "resource_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Resource resource;

    @JsonBackReference
    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name = "resource_column_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ResourceColumn resourceColumn;

   // @JsonBackReference
    public ResourceColumn getResourceColumn() {
        return resourceColumn;
    }

    public void setResourceColumn(ResourceColumn resourceColumn) {
        this.resourceColumn = resourceColumn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResourceExtraItemValue() {
        return resourceExtraItemValue;
    }

    public void setResourceExtraItemValue(String resourceExtraItemValue) {
        this.resourceExtraItemValue = resourceExtraItemValue;
    }

    public ResourceExtraItem() {
    }

//    public ResourceExtraItem(String resourceExtraItemValue, int resourceColumnId,  int resourceId  ) {
//        this.resourceExtraItemValue = resourceExtraItemValue;
//        this.resourceColumnId = resourceColumnId;
//        this.resourceId = resourceId;
//
//    }
}
