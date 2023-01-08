package com.fmiunibuc.FoodDeliveryApp.services.impl;

import com.fmiunibuc.FoodDeliveryApp.entities.Driver;
import com.fmiunibuc.FoodDeliveryApp.entities.Product;
import com.fmiunibuc.FoodDeliveryApp.entities.Restaurant;
import com.fmiunibuc.FoodDeliveryApp.repositories.DriverRepository;
import com.fmiunibuc.FoodDeliveryApp.repositories.ProductRepository;
import com.fmiunibuc.FoodDeliveryApp.repositories.RestaurantRepository;
import com.fmiunibuc.FoodDeliveryApp.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Driver getDriverById(int id){
        return driverRepository.findById(id).get();
    }

    @Override
    public List<Driver> getDriversByRestaurantId(int restaurantId){
        return driverRepository.findAllByRestaurantId(restaurantId);
    }
    @Override
    public Driver addDriver(Driver driver, int restaurantId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        driver.setRestaurant(restaurant);
        return driverRepository.save(driver);
    };

    @Override
    public void updateDriver(int id, Driver driver){
        Driver driverUpdated = driverRepository.findById(id).get();
        driverUpdated.setName(driver.getName());
        driverUpdated.setPhonenumber(driver.getPhonenumber());
        driverRepository.save(driverUpdated);
    }

    @Override
    public void removeDriver(int id){
        driverRepository.deleteById(id);
    };
}
