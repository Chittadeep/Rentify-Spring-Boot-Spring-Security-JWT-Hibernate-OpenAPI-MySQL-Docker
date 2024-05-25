package com.example.Rentify.Services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public List<PropertyModel> getPropertiesOfSeller()
    {
        return propertyRepository.getPropertyBySeller(authorizeSeller()).stream().map(PropertyModel::new).toList();
    }

    public List<Property> getPropertiesOfSellerWithInterestedBuyers()
    {
        return propertyRepository.getPropertyBySeller(authorizeSeller());
    }

    public PropertyModel interestedBuyer(int id)
    {
        Property property = getPropertyEntity(id);
        Buyer buyer = authorizeBuyer();
        List<Property> interestedProperties = buyer.getInterestedProperties();
        if(interestedProperties.contains(property)) throw new BuyerWasAlreadyInterestedException();
        interestedProperties.add(property);
        buyer.setInterestedProperties(interestedProperties);
        buyerRepository.save(buyer);
        return new PropertyModel(property);
    }

    public PropertyModel disinterestedBuyer(int id)
    {
        Property property = getPropertyEntity(id);
        Buyer buyer = authorizeBuyer();
        List<Buyer> interestedBuyer = property.getInterestedBuyers();
        if(interestedBuyer.contains(authorizeBuyer())){
        interestedBuyer.remove(authorizeBuyer());
        }
        else throw new BuyerWasNotPreviouslyInterestedException();
        property.setInterestedBuyers(interestedBuyer);
        List<Property> interestedProperties = buyer.getInterestedProperties();
        interestedProperties.remove(property);
        buyer.setInterestedProperties(interestedProperties);
        buyerRepository.save(buyer);
        propertyRepository.save(property);
        return new PropertyModel(property);
    }

    public boolean deleteProperty(int id)
    {
        Property property = getPropertyEntity(id);
        if(property.getSeller().equals(authorizeSeller()))
            propertyRepository.delete(property);
        else
            throw new SellerNotMatchingException();
        return true;
    }

    public Set<Buyer> getInterestedBuyers()
    {
        List<Property> properties = getPropertiesOfSellerWithInterestedBuyers();
        Set<Buyer> interestedBuyers = new HashSet<>();
        properties.forEach(property->interestedBuyers.addAll(property.getInterestedBuyers()));
        return interestedBuyers;
    }

    public Property updateProperty(PropertyModel property)
    {
        Property oldProperty = getPropertyEntity(property.getId());
        if(!oldProperty.getSeller().equals(authorizeSeller())) throw new SellerNotMatchingException();
        oldProperty.setArea(property.getArea());
        oldProperty.setBathroom(property.getBathroom());
        oldProperty.setColleges(property.getColleges());
        oldProperty.setHospitals(property.getHospitals());
        oldProperty.setNumberOfBedrooms(property.getNumberOfBedrooms());
        oldProperty.setPlace(property.getPlace());
        propertyRepository.save(oldProperty);
        return oldProperty;
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
