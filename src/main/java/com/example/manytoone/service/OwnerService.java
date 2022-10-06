package com.example.manytoone.service;

import java.util.List;

import com.example.manytoone.model.Owner;

public interface OwnerService {
    List<Owner> getAllOwners();

    Owner getOwner(long id);

    List<Owner> getOwnerByName(String name);

    void createOwner(Owner owner);

    void editOwner(long id, Owner owner);

    void deleteOwner(long id);

    void deleteAllOwner();

}
