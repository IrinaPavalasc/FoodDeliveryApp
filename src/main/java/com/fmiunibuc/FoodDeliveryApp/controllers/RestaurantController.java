package com.fmiunibuc.FoodDeliveryApp.controllers;

import com.fmiunibuc.FoodDeliveryApp.entities.Restaurant;
import com.fmiunibuc.FoodDeliveryApp.services.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Restaurant Controller", description = "Manage Restaurants")
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Operation(summary = "Get Restaurant")
    @GetMapping("/restaurant/{id}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable int id){
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @Operation(summary = "Get All Restaurants")
    @GetMapping("/restaurant/all")
    public ResponseEntity<List<Restaurant>> getRestaurantList(){
        List<Restaurant> restaurantList = (List<Restaurant>) restaurantService.getRestaurantList();
        return new ResponseEntity<>(restaurantList, HttpStatus.OK);
    }

    @Operation(summary = "Update Restaurant")
    @PutMapping("restaurant/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable int id, @RequestBody @Valid Restaurant restaurant){
        restaurantService.updateRestaurant(id, restaurant);
        return new ResponseEntity<>(restaurantService.getRestaurantById(id), HttpStatus.OK);
    }

    @Operation(summary = "Add Restaurant")
    @PostMapping("/restaurant/add")
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody @Valid Restaurant restaurant){
        return new ResponseEntity<>(restaurantService.addRestaurant(restaurant), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete Restaurant")
    @DeleteMapping("/restaurant/{id}")
    public ResponseEntity<HttpStatus> removeRestaurant(@PathVariable int id){
        restaurantService.removeRestaurant(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
