package com.example.Rentify.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.Rentify.Entities.Buyer;
import com.example.Rentify.Entities.Property;
import com.example.Rentify.Entities.Seller;
import com.example.Rentify.Exceptions.BuyerWasAlreadyInterestedException;
import com.example.Rentify.Exceptions.BuyerWasNotPreviouslyInterestedException;
import com.example.Rentify.Exceptions.PropertyNotFoundException;
import com.example.Rentify.Exceptions.SellerNotMatchingException;
import com.example.Rentify.Model.PropertyModel;
import com.example.Rentify.Repositories.BuyerRepository;
import com.example.Rentify.Repositories.PropertyRepository;
import com.example.Rentify.Repositories.SellerRepository;


@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private BuyerRepository buyerRepository;
    
    public PropertyModel createProperty(Property property)
    {
        System.out.println(property.getPlace());
        property.setSeller(authorizeSeller());
        propertyRepository.save(property);
        return new PropertyModel(property);
    }

    public PropertyModel getProperty(int id)
    {
        return new PropertyModel(propertyRepository.findById(id).orElseThrow(()-> new PropertyNotFoundException()));
    }

    
    private Property getPropertyEntity(int id)
    {
        return propertyRepository.findById(id).orElseThrow(()-> new PropertyNotFoundException());
    }

    public List<PropertyModel> getAllProperties()
    {
        return ((List<Property>) propertyRepository.findAll()).stream().map(PropertyModel::new).toList();
    }

    public Property updateProperty(Property property)
    {
        Property propertyObj = getPropertyEntity(property.getId());
        if(propertyObj.getSeller().getId()!=authorizeSeller().getId()) throw new SellerNotMatchingException();
        propertyRepository.save(property);
        return property;
    }

    public PropertyModel interestedBuyer(int id)
    {
        
        System.out.println("Before start" + getPropertyEntity(id).getInterestedBuyers());
        Property property = getPropertyEntity(id);
        Buyer buyer = authorizeBuyer();
        List<Buyer> interestedBuyer = property.getInterestedBuyers();
        if(interestedBuyer.contains(buyer)) throw new BuyerWasAlreadyInterestedException();
        interestedBuyer.add(buyer);
        property.setInterestedBuyers(interestedBuyer);
        propertyRepository.save(property);
        buyerRepository.save(buyer);
        return new PropertyModel(property);
    }

    public PropertyModel disinterestedBuyer(int id)
    {
        Property property = getPropertyEntity(id);
        List<Buyer> interestedBuyer = property.getInterestedBuyers();
        if(interestedBuyer.contains(authorizeBuyer())){
        interestedBuyer.remove(authorizeBuyer());
        }
        else throw new BuyerWasNotPreviouslyInterestedException();
        property.setInterestedBuyers(interestedBuyer);
        propertyRepository.save(property);
        return new PropertyModel(property);
    }

    private Seller authorizeSeller()
    {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Seller seller = sellerRepository.getSellerByEmail(userDetails.getUsername());
        return seller;
    }

    private Buyer authorizeBuyer()
    {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Buyer buyer = buyerRepository.getBuyerByEmail(userDetails.getUsername());
        return buyer;        
    }

}
