package com.example.Rentify.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Rentify.Entities.Property;

@Repository
public interface PropertyRepository extends CrudRepository<Property, Integer>{
}
