package com.example.manytoone.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.manytoone.model.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Optional<List<Owner>> findByName(String name);

}
