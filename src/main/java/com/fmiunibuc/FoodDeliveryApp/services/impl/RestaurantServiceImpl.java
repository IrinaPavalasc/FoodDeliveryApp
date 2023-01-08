package com.fmiunibuc.FoodDeliveryApp.services.impl;

import com.fmiunibuc.FoodDeliveryApp.entities.Restaurant;
import com.fmiunibuc.FoodDeliveryApp.repositories.RestaurantRepository;
import com.fmiunibuc.FoodDeliveryApp.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Restaurant getRestaurantById(int id) {return restaurantRepository.findById(id).get();}

    @Override
    public List<Restaurant> getRestaurantList() {
        return (List<Restaurant>) restaurantRepository.findAll();
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void updateRestaurant(int id, Restaurant restaurant){
        Restaurant restaurantUpdated = restaurantRepository.findById(id).get();
        restaurantUpdated.setName(restaurant.getName());
        restaurantUpdated.setAddress(restaurant.getAddress());
        restaurantUpdated.setSchedule(restaurant.getSchedule());
        restaurantRepository.save(restaurantUpdated);
    }

    @Override
    public void removeRestaurant(int id){
        restaurantRepository.deleteById(id);
    };
}
