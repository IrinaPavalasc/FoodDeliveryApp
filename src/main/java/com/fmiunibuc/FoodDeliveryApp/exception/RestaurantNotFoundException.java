package com.fmiunibuc.FoodDeliveryApp.exception;

public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException(int id) {
        super("The restaurant with id: '" + id + "' does not exist.");
    }
    
}