package com.fmiunibuc.FoodDeliveryApp.repositories;

import com.fmiunibuc.FoodDeliveryApp.entities.Driver;
import com.fmiunibuc.FoodDeliveryApp.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DriverRepository extends CrudRepository<Driver, Integer> {

    List<Driver> findAllByRestaurantId(int restaurantId);
}
