package com.example.manytoone.service;

import java.util.List;

import com.example.manytoone.model.Property;

public interface PropertyService {
    List<Property> getAllProperties(long ownerId);

    Property getProperty(long ownerId, long propertyId);

    void createProperty(long ownerId, Property property);

    void editProperty(long ownerId, long propertyId, Property property);

    void deleteProperty(long propertyId);

    void deletePropertyFromOwnerId(long ownerId, long propertyId);

    void deleteAll(long ownerId);
}
