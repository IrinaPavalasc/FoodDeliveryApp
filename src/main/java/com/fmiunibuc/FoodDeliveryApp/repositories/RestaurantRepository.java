package com.fmiunibuc.FoodDeliveryApp.repositories;

import com.fmiunibuc.FoodDeliveryApp.entities.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {
}