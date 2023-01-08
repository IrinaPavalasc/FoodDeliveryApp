package com.fmiunibuc.FoodDeliveryApp.services;

import com.fmiunibuc.FoodDeliveryApp.entities.Restaurant;

import java.util.List;

public interface RestaurantService {

    Restaurant getRestaurantById(int id);

    List<Restaurant> getRestaurantList();

    Restaurant addRestaurant(Restaurant restaurant);

    void updateRestaurant(int id, Restaurant restaurant);

    void removeRestaurant(int id);
}
