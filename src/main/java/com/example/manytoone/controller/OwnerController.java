package com.example.manytoone.controller;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.NoSuchElementException;

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

import com.example.manytoone.exception.NoSuchElementFoundException;
import com.example.manytoone.model.Owner;
import com.example.manytoone.repository.OwnerRepository;

@RestController
@RequestMapping(path = "/api")
public class OwnerController {

    private OwnerRepository ownerRepository;

    public OwnerController(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    // Get all owners
    @GetMapping("/owners")
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    // Get an owner by Id
    @GetMapping("/owners/{id}")
    public Owner getOwner(@PathVariable(name = "id") long id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementFoundException("Owner not found for id " + id));

    }

    // Get by name
    @GetMapping("/ownerName/{name}")
    public List<Owner> getOwnerByName(@PathVariable(name = "name") String name) {
        return ownerRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Nothing found for name " + name));
    }

    // Create an owner
    @PostMapping("/owners")
    public ResponseEntity<Owner> createOwner(@RequestBody Owner owner) {
        Owner savedOwner = ownerRepository.save(owner);
        return new ResponseEntity<>(savedOwner, HttpStatus.OK);
    }

    // Edit an owner
    @PutMapping("/owners/{id}")
    public Owner editOwner(@PathVariable(name = "id") long id, @RequestBody Owner owner) {
        Owner actualOwner = ownerRepository.findById(id).orElseThrow(() -> new RuntimeException("Owner not found"));
        actualOwner.setName(owner.getName());
        actualOwner.setAge(owner.getAge());
        actualOwner.setGender(owner.getGender());
        return ownerRepository.save(actualOwner);
    }

    // delete an owner
    @DeleteMapping("/owners/{id}")
    public HttpStatus deleteOwner(@PathVariable(name = "id") long id) {
        if (!ownerRepository.existsById(id)) {
            throw new NoSuchElementFoundException("Owner doesn't exist for id " + id);
        }
        ownerRepository.deleteById(id);
        return HttpStatus.OK;
    }

    // *optionnal* delete all owners
    @DeleteMapping("/owners")
    public HttpStatus deleteAllOwners() {
        ownerRepository.deleteAll();
        return HttpStatus.OK;
    }

}
