package com.example.manytoone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.manytoone.model.Property;
import com.example.manytoone.service.PropertyService;

@RestController
@RequestMapping(path = "/api")
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    // Get all properties from an owner
    @GetMapping("/owners/{ownerId}/properties")
    public List<Property> getAllProperties(@PathVariable(value = "ownerId") long ownerId) {
        return propertyService.getAllProperties(ownerId);
    }

    // Create a property and add an owner
    @PostMapping("/owners/{ownerId}/properties")
    public ResponseEntity<?> addProperty(@PathVariable(value = "ownerId") long ownerId,
            @RequestBody Property property) {
        propertyService.createProperty(ownerId, property);
        return new ResponseEntity<>("Property added to " + ownerId, HttpStatus.OK);
    }

    // Delete a property
    @DeleteMapping("/properties/{propertyId}")
    public ResponseEntity<?> deleteProperty(@PathVariable(name = "ownerId") long ownerId,
            @PathVariable(name = "propertyId") long propertyId) {
        return new ResponseEntity<>("Property deleted", HttpStatus.OK);
    }

    // Delete all properties from an owner
    @DeleteMapping("/owners/{ownerId}/properties")
    public ResponseEntity<?> deletePropertyFromOwner(@PathVariable(name = "ownerId") long ownerId) {
        propertyService.deleteAll(ownerId);
        return new ResponseEntity<>("All deleted successfully", HttpStatus.OK);
    }

    // Edit a property from an owner

    @PutMapping("/owners/{ownerId}/properties/{propertyId}")
    public ResponseEntity<?> editProperty(@PathVariable(name = "ownerId") long ownerId,
            @PathVariable(value = "propertyId") long propertyId, @RequestBody Property property) {
        propertyService.editProperty(ownerId, propertyId, property);
        return new ResponseEntity<>("Property " + propertyId + " has been edited", HttpStatus.OK);
    }
}
