package com.fmiunibuc.FoodDeliveryApp.exception;

public class DriverNotFoundException extends RuntimeException{

    public DriverNotFoundException() {
        super("No driver found!");
    }
}
