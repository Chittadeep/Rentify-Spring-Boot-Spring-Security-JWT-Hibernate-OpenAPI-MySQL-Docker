package com.example.Rentify.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Rentify.Entities.Buyer;
import com.example.Rentify.Entities.LoginDetails;
import com.example.Rentify.Entities.UserType;
import com.example.Rentify.Exceptions.BuyerNotFoundException;
import com.example.Rentify.Model.UserModel;
import com.example.Rentify.Repositories.BuyerRepository;
import com.example.Rentify.Repositories.LoginDetailsRepository;

@Service
public class BuyerService {
    
    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private LoginDetailsRepository loginDetailsRepository;
    
    public Buyer createBuyer(UserModel user)
    {
        Buyer buyer = new Buyer(user);
        buyerRepository.save(buyer);
        loginDetailsRepository.save(new LoginDetails(user, UserType.BUYER));
        return buyer;
    }

    public Buyer getBuyer(int id)
    {
        return buyerRepository.findById(id).orElseThrow(()->new BuyerNotFoundException());
    } 

    public List<Buyer> getAllBuyer()
    {
        return (List<Buyer>) buyerRepository.findAll();
    }

    public Buyer updateBuyer(Buyer buyer)
    {
        getBuyer(buyer.getId());
        buyerRepository.save(buyer);
        return buyer;
    }
}
