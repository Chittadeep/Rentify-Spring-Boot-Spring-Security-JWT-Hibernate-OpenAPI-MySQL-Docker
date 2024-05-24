package com.example.Rentify.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.Rentify.Entities.LoginDetails;

public interface LoginDetailsRepository extends CrudRepository<LoginDetails, Integer>{
    public LoginDetails getLoginDetailsByEmail(String email);
}
