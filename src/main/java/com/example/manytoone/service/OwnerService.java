package com.example.manytoone.service;

import java.util.List;
import java.util.Optional;

import com.example.manytoone.model.Owner;

public interface OwnerService {
    List<Owner> getAllOwners();

    Optional<Owner> findOwnerById(long id);

    List<Owner> getOwnerByName(String name);

    Owner createOwner(Owner owner);

    void editOwner(long id, Owner owner);

    void deleteOwner(long id);

    void deleteAllOwner();

}
