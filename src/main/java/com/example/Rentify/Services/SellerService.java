package com.example.Rentify.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Rentify.Entities.LoginDetails;
import com.example.Rentify.Entities.Seller;
import com.example.Rentify.Entities.UserType;
import com.example.Rentify.Exceptions.SellerNotFoundException;
import com.example.Rentify.Model.UserModel;
import com.example.Rentify.Repositories.LoginDetailsRepository;
import com.example.Rentify.Repositories.SellerRepository;

@Service
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private LoginDetailsRepository  loginDetailsRepository;

    public Seller createSeller(UserModel user)
    {
        Seller seller = new Seller(user);
        sellerRepository.save(seller);
        loginDetailsRepository.save(new LoginDetails(user, UserType.SELLER));
        return seller;
    }

    public Seller getSeller(int id)
    {
        return sellerRepository.findById(id).orElseThrow(()->new SellerNotFoundException());
    }

    public List<Seller> getAllSellers()
    {
        return (List<Seller>) sellerRepository.findAll();
    }
    
    public Seller updateSeller(Seller seller)
    {
        getSeller(seller.getId());
        sellerRepository.save(seller);
        return seller;
    }
}
