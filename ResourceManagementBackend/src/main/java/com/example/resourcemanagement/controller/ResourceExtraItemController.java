package com.example.resourcemanagement.controller;

import com.example.resourcemanagement.entity.ResourceExtraItem;
import com.example.resourcemanagement.service.ResourceExtraItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/resourceitem")
public class ResourceExtraItemController {

    @Autowired
    private ResourceExtraItemService resourceExtraItemService;

    @GetMapping("/{itemId}")
    public ResponseEntity<?> getResourceExtraItemById(@PathVariable int itemId) {
        Optional<ResourceExtraItem> result = resourceExtraItemService.getResourceExtraItemById(itemId);
        if (result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Column Item Not Found!", HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<?> createResourceExtraItem(@RequestBody ResourceExtraItem resourceExtraItem) {
        Optional<ResourceExtraItem> res = resourceExtraItemService.createResourceExtraItem(resourceExtraItem);
        return new ResponseEntity<>(res.get(), HttpStatus.OK);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<?> updateResourceExtraItem(@RequestBody ResourceExtraItem resourceExtraItem, @PathVariable int itemId) {
        Optional<ResourceExtraItem> res = resourceExtraItemService.updateResourceExtraItem(resourceExtraItem, itemId);
        if (res.isPresent()) {
            return new ResponseEntity<>(res.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Column Item Not Found!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<?> deleteResourceExtraItemById(@PathVariable int itemId) {
        Optional<ResourceExtraItem> existResourceExtraItem = resourceExtraItemService.deleteResourceExtraItemById(itemId);
        if (existResourceExtraItem.isPresent()) {
            return new ResponseEntity<>("Deleted Column Item Successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Column Item Not Found!", HttpStatus.NOT_FOUND);
    }
}
