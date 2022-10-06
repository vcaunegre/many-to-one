package com.example.manytoone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.manytoone.exception.NoSuchElementFoundException;
import com.example.manytoone.model.Owner;
import com.example.manytoone.model.Property;
import com.example.manytoone.repository.OwnerRepository;
import com.example.manytoone.repository.PropertyRepository;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    OwnerRepository ownerRepository;

    @Override
    public List<Property> getAllProperties(long ownerId) {
        return propertyRepository.findByOwnerId(ownerId)
                .orElseThrow(() -> new NoSuchElementFoundException("No ownerId"));
    }

    @Override
    public Property getProperty(long ownerId, long propertyId) {
        return propertyRepository.findByIdAndOwnerId(ownerId, propertyId)
                .orElseThrow(() -> new NoSuchElementFoundException("No property or owner"));
    }

    @Override
    public void createProperty(long ownerId, Property property) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new NoSuchElementFoundException("Owner doesn't exist"));
        property.setOwner(owner);
        propertyRepository.save(property);

    }

    @Override
    public void editProperty(long ownerId, long propertyId, Property property) {
        if (ownerRepository.existsById(ownerId) && propertyRepository.existsById(propertyId)) {
            Property myProperty = propertyRepository.findByIdAndOwnerId(propertyId, ownerId)
                    .orElseThrow(() -> new NoSuchElementFoundException("No property with this id"));
            if (property.getCity() != null) {
                myProperty.setCity(property.getCity());
            }
            if (property.getCountry() != null)
                myProperty.setCountry(property.getCountry());
            if (property.getStreet() != null) {
                myProperty.setStreet(property.getStreet());
            }
            if (property.getPrice() != 0) {
                myProperty.setPrice(property.getPrice());
            }
            propertyRepository.save(myProperty);
        }
    }

    @Override
    public void deletePropertyFromOwnerId(long ownerId, long propertyId) {
        if (ownerRepository.existsById(ownerId)) {
            Optional<Property> property = propertyRepository.findByIdAndOwnerId(propertyId, ownerId);
            if (property != null) {

            }
        }
    }

    @Override
    public void deleteProperty(long propertyId) {
        if (propertyRepository.existsById(propertyId)) {
            throw new NoSuchElementFoundException("No property found for this id");
        }
        propertyRepository.deleteById(propertyId);
    }

    @Override
    public void deleteAll(long ownerId) {
        propertyRepository.findByOwnerId(ownerId).map(property -> {
            propertyRepository.deleteAll(property);
            return true;
        })
                .orElseThrow(() -> new NoSuchElementFoundException("No owner matching ownerId"));
    }

}
