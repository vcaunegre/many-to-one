package com.example.manytoone.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.manytoone.model.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    Optional<List<Property>> findByOwnerId(Long id);

    Optional<Property> findByIdAndOwnerId(Long id, Long ownerId);

    Long deleteAllByOwnerId(Long ownerId);
}
