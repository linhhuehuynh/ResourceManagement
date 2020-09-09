package com.example.demoresourcemanagement.controller;

import com.example.demoresourcemanagement.entity.Resource;
import com.example.demoresourcemanagement.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping("")
    public List<Resource> getAll(){
        return resourceService.getAllResources();
    }

    @PostMapping("")
    public Resource addResource(@RequestBody Resource resource){
        return resourceService.addResource(resource);
    }

    @DeleteMapping("/{id}")
    public String deleteResourceById(@PathVariable int id){
        resourceService.deleteResourceById(id);
        return "Deleted resource id " + id;
    }

    @DeleteMapping("")
    public String deleteAll(){
        resourceService.deleteAllResources();
        return "Deleted all resources succesfully!";
    }

    @PutMapping("")
    public Resource updateResource(Resource resource){
        return resourceService.updateResource(resource);
    }
}

