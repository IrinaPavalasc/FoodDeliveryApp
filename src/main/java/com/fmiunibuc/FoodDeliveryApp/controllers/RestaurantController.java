package com.fmiunibuc.FoodDeliveryApp.controllers;

import com.fmiunibuc.FoodDeliveryApp.entities.Restaurant;
import com.fmiunibuc.FoodDeliveryApp.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable int id){
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/restaurant/all")
    public ResponseEntity<List<Restaurant>> getRestaurantList(){
        List<Restaurant> restaurantList = (List<Restaurant>) restaurantService.getRestaurantList();
        return new ResponseEntity<>(restaurantList, HttpStatus.OK);
    }

    @PutMapping("restaurant/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable int id, @RequestBody @Valid Restaurant restaurant){
        restaurantService.updateRestaurant(id, restaurant);
        return new ResponseEntity<>(restaurantService.getRestaurantById(id), HttpStatus.OK);
    }

    @PostMapping("/restaurant/add")
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody @Valid Restaurant restaurant){
        return new ResponseEntity<>(restaurantService.addRestaurant(restaurant), HttpStatus.CREATED);
    }

    @DeleteMapping("/restaurant/{id}")
    public ResponseEntity<HttpStatus> removeRestaurant(@PathVariable int id){
        restaurantService.removeRestaurant(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
