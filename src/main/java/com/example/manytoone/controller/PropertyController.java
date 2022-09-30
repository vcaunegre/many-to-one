package com.example.manytoone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.manytoone.exception.NoSuchElementFoundException;
import com.example.manytoone.model.Owner;
import com.example.manytoone.model.Property;
import com.example.manytoone.repository.OwnerRepository;
import com.example.manytoone.repository.PropertyRepository;

@RestController
@RequestMapping(path = "/api")
public class PropertyController {

    private OwnerRepository ownerRepository;
    private PropertyRepository propertyRepository;

    public PropertyController(@Autowired OwnerRepository ownerRepository,
            @Autowired PropertyRepository propertyRepository) {
        this.ownerRepository = ownerRepository;
        this.propertyRepository = propertyRepository;
    }

    // Get all properties from an owner
    @GetMapping("/owners/{ownerId}/properties")
    public List<Property> getAllProperties(@PathVariable(value = "ownerId") long ownerId) {
        return propertyRepository.findByOwnerId(ownerId)
                .orElseThrow(() -> new NoSuchElementFoundException("No ownerId"));
    }

    // Create a property and add an owner
    @PostMapping("/owners/{ownerId}/properties")
    public Property addProperty(@PathVariable(value = "ownerId") long ownerId, @RequestBody Property property) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new NoSuchElementFoundException("Owner doesn't exist"));
        property.setOwner(owner);
        return propertyRepository.save(property);
    }

    // Delete a property
    @DeleteMapping("/properties/{propertyId}")
    public ResponseEntity<?> deleteProperty(@PathVariable(name = "ownerId") long ownerId,
            @PathVariable(name = "propertyId") long propertyId) {
        if (propertyRepository.existsById(propertyId)) {
            throw new NoSuchElementFoundException("No property found for this id");
        }
        propertyRepository.deleteById(propertyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Delete all properties from an owner
    @DeleteMapping("/owners/{ownerId}/properties")
    public ResponseEntity<?> deletePropertyFromOwner(@PathVariable(name = "ownerId") long ownerId) {
        return propertyRepository.findByOwnerId(ownerId).map(property -> {
            propertyRepository.deleteAll(property);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new NoSuchElementFoundException("No owner matching ownerId"));
    }

    // Edit a property from an owner
}
