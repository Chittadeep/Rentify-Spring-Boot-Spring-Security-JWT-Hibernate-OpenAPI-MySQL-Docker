package com.example.Rentify.Controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PatchExchange;

import com.example.Rentify.Entities.Buyer;
import com.example.Rentify.Entities.Property;
import com.example.Rentify.Entities.Seller;
import com.example.Rentify.Model.PropertyModel;
import com.example.Rentify.Services.PropertyService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @PostMapping("/register/property")
    public ResponseEntity<PropertyModel> registerProperty(@RequestBody Property property) {
        return new ResponseEntity<PropertyModel>(propertyService.createProperty(property), HttpStatus.CREATED);
    }
    
    @GetMapping("/allProperty")
    public ResponseEntity<List<PropertyModel>> getAllProperties() {
        return new ResponseEntity<List<PropertyModel>>(propertyService.getAllProperties(), HttpStatus.OK);
    }
    
    @GetMapping("/property/{id}")
    public ResponseEntity<PropertyModel> getProperty(@PathVariable int id) {
        return new ResponseEntity<PropertyModel>(propertyService.getProperty(id), HttpStatus.OK);
    }
    
    @PatchMapping("/interested/property/{id}")
    public ResponseEntity<Seller> interestedProperty(@PathVariable int id)
    {
        return new ResponseEntity<Seller>(propertyService.interestedBuyer(id), HttpStatus.OK);
    }

    @PatchMapping("/disinterested/property/{id}")
    public ResponseEntity<PropertyModel> disinterestedProperty(@PathVariable int id)
    {
        return new ResponseEntity<PropertyModel>(propertyService.disinterestedBuyer(id), HttpStatus.OK);
    }

    @GetMapping("/getPropertiesOfTheSeller")
    public ResponseEntity<List<PropertyModel>> getAllPropertiesOfTheSeller()
    {
        return new ResponseEntity<List<PropertyModel>>(propertyService.getPropertiesOfSeller(), HttpStatus.OK);
    }

    @GetMapping("/getPropertiesOfTheSellerWithInterestedBuyers")
    public ResponseEntity<List<Property>> getAllPropertiesOfTheSellerWithInterestedBuyers()
    {
        return new ResponseEntity<List<Property>>(propertyService.getPropertiesOfSellerWithInterestedBuyers(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/property/{id}")
    public ResponseEntity<Boolean> deleteProperty(@PathVariable int id)
    {
        return new ResponseEntity<Boolean>(propertyService.deleteProperty(id), HttpStatus.OK);
    }
    
    @PutMapping("/update/property")
    public ResponseEntity<Property> updateProperty(@RequestBody PropertyModel propertyModel)
    {
        return new ResponseEntity<Property>(propertyService.updateProperty(propertyModel), HttpStatus.OK);
    }

    @GetMapping("/getInterestedBuyers")
    public ResponseEntity<Set<Buyer>> getInterestedBuyers()
    {
        return new ResponseEntity<Set<Buyer>>(propertyService.getInterestedBuyers(), HttpStatus.OK);
    }

    
}
