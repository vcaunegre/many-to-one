package com.example.manytoone;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.manytoone.model.Owner;
import com.example.manytoone.repository.OwnerRepository;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OwnerRepositoryTest {

    @Autowired
    OwnerRepository ownerRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveOwnerTest() {

        Owner owner = new Owner("tom", 35, 'M');

        ownerRepository.save(owner);

        Assertions.assertThat(owner.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getOwnerTest() {
        Owner owner = ownerRepository.findById(1L).get();

        Assertions.assertThat(owner.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    public void getListOfOwnersTest() {
        List<Owner> owners = ownerRepository.findAll();

        Assertions.assertThat(owners.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    public void updateOwnerTest() {
        Owner owner = ownerRepository.findById(1L).get();

        owner.setName("tim");

        Owner ownerUpdated = ownerRepository.save(owner);

        Assertions.assertThat(ownerUpdated.getName()).isEqualTo("tim");

    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteOwnerTest() {
        Owner owner = ownerRepository.findById(1L).get();

        ownerRepository.delete(owner);

        List<Owner> owner1 = new ArrayList<>();

        Optional<List<Owner>> optionalOwner = ownerRepository.findByName("tim");

        if (optionalOwner.isPresent()) {
            owner1 = optionalOwner.get();
        }

        Assertions.assertThat(owner1.size()).isEqualTo(0);
    }
}
