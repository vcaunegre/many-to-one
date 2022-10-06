package com.example.manytoone.controller;

import java.util.List;
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

import com.example.manytoone.model.Owner;
import com.example.manytoone.service.OwnerService;

@RestController
@RequestMapping(path = "/api")
public class OwnerController {

    private OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    // Get all owners
    @GetMapping("/owners")
    public List<Owner> getAllOwners() {
        return ownerService.getAllOwners();
    }

    // Get an owner by Id
    @GetMapping("/owners/{id}")
    public Owner getOwner(@PathVariable(name = "id") long id) {
        return ownerService.getOwner(id);
    }

    // Get by name
    @GetMapping("/ownerName/{name}")
    public List<Owner> getOwnerByName(@PathVariable(name = "name") String name) {
        return ownerService.getOwnerByName(name);
    }

    // Create an owner
    @PostMapping("/owners")
    public ResponseEntity<?> createOwner(@RequestBody Owner owner) {
        ownerService.createOwner(owner);
        return new ResponseEntity<>("Owner created", HttpStatus.OK);
    }

    // Edit an owner
    @PutMapping("/owners/{id}")
    public ResponseEntity<?> editOwner(@PathVariable(name = "id") long id, @RequestBody Owner owner) {
        ownerService.editOwner(id, owner);
        return new ResponseEntity<>("Owner edited successfully", HttpStatus.OK);
    }

    // delete an owner
    @DeleteMapping("/owners/{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable(name = "id") long id) {
        ownerService.deleteOwner(id);
        return new ResponseEntity<>("Owner deleted successfully", HttpStatus.OK);
    }

    // *optionnal* delete all owners
    @DeleteMapping("/owners")
    public HttpStatus deleteAllOwners() {
        ownerService.deleteAllOwner();
        return HttpStatus.OK;
    }

}
