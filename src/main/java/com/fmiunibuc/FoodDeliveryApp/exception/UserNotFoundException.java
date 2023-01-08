package com.fmiunibuc.FoodDeliveryApp.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(int id) {

        super("The user with id: '" + id + "' does not exist.");
    }
    
}