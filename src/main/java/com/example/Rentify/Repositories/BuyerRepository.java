package com.example.Rentify.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Rentify.Entities.Buyer;

@Repository
public interface BuyerRepository extends CrudRepository<Buyer, Integer>  {
    public Buyer getBuyerByEmail(String email);
}
