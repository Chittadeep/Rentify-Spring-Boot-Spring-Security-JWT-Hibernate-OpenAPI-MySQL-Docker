package com.example.Rentify.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.Rentify.Entities.Seller;
import com.example.Rentify.Model.UserModel;
import com.example.Rentify.Services.SellerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @PostMapping("/register/seller")
    public ResponseEntity<Seller> createSeller(@RequestBody UserModel user) {     
        return new ResponseEntity<Seller>(sellerService.createSeller(user), HttpStatus.CREATED);
    }
    
    @GetMapping("/getSeller/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable int id) {
        return new ResponseEntity<Seller>(sellerService.getSeller(id), HttpStatus.OK);
    }
    

}
