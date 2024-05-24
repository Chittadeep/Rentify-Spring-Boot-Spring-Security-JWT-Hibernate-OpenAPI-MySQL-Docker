package com.example.Rentify.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.Rentify.Entities.Buyer;
import com.example.Rentify.Model.UserModel;
import com.example.Rentify.Services.BuyerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class BuyerController {
    @Autowired
    private BuyerService buyerService;

    @PostMapping("/register/buyer")
    public ResponseEntity<Buyer> createBuyer(@RequestBody UserModel user) {        
        return new ResponseEntity<Buyer>(buyerService.createBuyer(user), HttpStatus.CREATED);
    }

    @GetMapping("/getBuyer/{id}")
    public ResponseEntity<Buyer> getBuyerById(@PathVariable int id) {
        return new ResponseEntity<Buyer>(buyerService.getBuyer(id), HttpStatus.OK);
    }
    
    
}
