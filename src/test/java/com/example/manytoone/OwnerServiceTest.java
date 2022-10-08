package com.example.manytoone;

import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.manytoone.model.Owner;
import com.example.manytoone.repository.OwnerRepository;
import com.example.manytoone.service.OwnerService;

@SpringBootTest
public class OwnerServiceTest {

    @Autowired
    private OwnerService service;

    @MockBean
    private OwnerRepository repository;

    @Test
    @DisplayName("Test getOwnerById successful")
    void testFindOwnerById() {
        Owner owner = new Owner("jimmy", 22, 'M');
        doReturn(Optional.of(owner)).when(repository).findById(owner.getId());

        //Execute the service call
        Optional<Owner> returnedOwner=service.findOwnerById(owner.getId());

        //Assert the response
        Assertions.assertTrue(returnedOwner.isPresent(),"Owner was not found");
        Assertions.assertSame(returnedOwner.get(), owner,"The owner returned was not the same as the mock");
    }

    @Test 
    @DisplayName("Test getOwnerById fail")
    void testGetOwnerByIdFail(){
        doReturn(Optional.empty()).when(repository).findById(1L);

         // Execute the service call
         Optional<Owner> returnedOwner = service.findOwnerById(1L);

         // Assert the response
         Assertions.assertFalse(returnedOwner.isPresent(), "Owner should not be found");
        }

    @Test
    @DisplayName("find all")
    void testFindAll() {
    // Setup our mock repository
    Owner widget1 = new Owner(1l, "Owner 1 Name",22, 'M');
    Owner widget2 = new Owner(2l, "Owner 2 Name", 22, 'M');
    doReturn(Arrays.asList(widget1, widget2)).when(repository).findAll();

    // Execute the service call
    List<Owner> owners = service.getAllOwners();

    // Assert the response
    Assertions.assertEquals(2, owners.size(), "findAll should return 2 owners");

    }

    @Test
@DisplayName("Test save owner")
void testSave() {
    // Setup our mock repository
    Owner owner = new Owner("Owner Name", 23,'M');
    doReturn(owner).when(repository).save(ArgumentMatchers.any());

    Owner createdOwner=service.createOwner(owner);


    // Assert the response
    Assertions.assertNotNull(createdOwner, "The saved owner should not be null");
}
}
