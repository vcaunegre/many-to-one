package com.example.manytoone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.manytoone.exception.NoSuchElementFoundException;
import com.example.manytoone.model.Owner;
import com.example.manytoone.repository.OwnerRepository;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    OwnerRepository ownerRepository;

    @Override
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();

    }

    @Override
    public Owner getOwner(long id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementFoundException("Owner not found for id " + id));

    }

    @Override
    public List<Owner> getOwnerByName(String name) {
        return ownerRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementFoundException("Nothing found for name " + name));

    }

    @Override
    public void createOwner(Owner owner) {
        ownerRepository.save(owner);

    }

    @Override
    public void deleteOwner(long id) {
        if (!ownerRepository.existsById(id)) {
            throw new NoSuchElementFoundException("Owner doesn't exist for id " + id);
        }
        ownerRepository.deleteById(id);
    }

    @Override
    public void deleteAllOwner() {
        ownerRepository.deleteAll();
    }

    @Override
    public void editOwner(long id, Owner owner) {
        Owner actualOwner = ownerRepository.findById(id).orElseThrow(() -> new RuntimeException("Owner not found"));
        actualOwner.setName(owner.getName());
        actualOwner.setAge(owner.getAge());
        actualOwner.setGender(owner.getGender());
        ownerRepository.save(actualOwner);
    }

}
