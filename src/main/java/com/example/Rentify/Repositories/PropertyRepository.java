package com.example.Rentify.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Rentify.Entities.Property;
import com.example.Rentify.Entities.Seller;

@Repository
public interface PropertyRepository extends CrudRepository<Property, Integer>{
    public List<Property> getPropertyBySeller(Seller seller);
}
