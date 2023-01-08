package com.fmiunibuc.FoodDeliveryApp.services.impl;

import com.fmiunibuc.FoodDeliveryApp.entities.Restaurant;
import com.fmiunibuc.FoodDeliveryApp.exception.RestaurantNotFoundException;
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
    public Restaurant getRestaurantById(int id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if(restaurant.isPresent()){
            return restaurant.get();
        } else {
            throw new RestaurantNotFoundException(id);
        }
    }

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
        Optional<Restaurant> restaurantUpdated = restaurantRepository.findById(id);
        if(restaurantUpdated.isPresent()){
            Restaurant restaurantUpdatedValue = restaurantUpdated.get();
            restaurantUpdatedValue.setName(restaurant.getName());
            restaurantUpdatedValue.setAddress(restaurant.getAddress());
            restaurantUpdatedValue.setSchedule(restaurant.getSchedule());
            restaurantRepository.save(restaurantUpdatedValue);
        } else {
            throw new RestaurantNotFoundException(id);
        }
    }

    @Override
    public void removeRestaurant(int id){
        restaurantRepository.deleteById(id);
    };
}
