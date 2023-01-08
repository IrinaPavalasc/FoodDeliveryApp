package com.fmiunibuc.FoodDeliveryApp.services;

import com.fmiunibuc.FoodDeliveryApp.entities.Driver;
import com.fmiunibuc.FoodDeliveryApp.entities.Product;

import java.util.List;

public interface DriverService {

    Driver getDriverById(int id);
    List<Driver> getDriversByRestaurantId(int restaurantId);
    Driver addDriver(Driver driver, int restaurantId);
    void updateDriver(int id, Driver driver);

    void removeDriver(int id);
}
