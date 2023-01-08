package com.fmiunibuc.FoodDeliveryApp.services.impl;

import com.fmiunibuc.FoodDeliveryApp.entities.Driver;
import com.fmiunibuc.FoodDeliveryApp.entities.Restaurant;
import com.fmiunibuc.FoodDeliveryApp.exception.DriverNotFoundException;
import com.fmiunibuc.FoodDeliveryApp.exception.RestaurantNotFoundException;
import com.fmiunibuc.FoodDeliveryApp.repositories.DriverRepository;
import com.fmiunibuc.FoodDeliveryApp.repositories.RestaurantRepository;
import com.fmiunibuc.FoodDeliveryApp.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Driver getDriverById(int id){
        Optional<Driver> driver = driverRepository.findById(id);
        if(driver.isPresent()){
            return driver.get();
        } else {
            throw new DriverNotFoundException();
        }
    }

    @Override
    public List<Driver> getDriversByRestaurantId(int restaurantId){
        return driverRepository.findAllByRestaurantId(restaurantId);
    }
    @Override
    public Driver addDriver(Driver driver, int restaurantId){
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if(restaurant.isPresent()){
            driver.setRestaurant(restaurant.get());
            return driverRepository.save(driver);
        } else {
            throw new RestaurantNotFoundException(restaurantId);
        }

    };

    @Override
    public void updateDriver(int id, Driver driver){
        Optional<Driver> driverUpdated = driverRepository.findById(id);
        if(driverUpdated.isPresent()){
            Driver driverUpdatedValue = driverUpdated.get();
            driverUpdatedValue.setName(driver.getName());
            driverUpdatedValue.setPhonenumber(driver.getPhonenumber());
            driverRepository.save(driverUpdatedValue);
        } else {
            throw new DriverNotFoundException();
        }

    }

    @Override
    public void removeDriver(int id){
        driverRepository.deleteById(id);
    };
}
