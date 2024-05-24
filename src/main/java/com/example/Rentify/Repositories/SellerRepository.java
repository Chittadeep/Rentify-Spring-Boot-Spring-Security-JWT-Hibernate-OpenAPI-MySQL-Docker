package com.example.Rentify.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Rentify.Entities.Seller;

@Repository
public interface SellerRepository extends CrudRepository<Seller, Integer> {
    public Seller getSellerByEmail(String email);
}
