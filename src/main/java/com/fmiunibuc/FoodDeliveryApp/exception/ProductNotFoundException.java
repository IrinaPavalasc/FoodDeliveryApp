package com.fmiunibuc.FoodDeliveryApp.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException() {
        super("No product found!");
    }
    
}