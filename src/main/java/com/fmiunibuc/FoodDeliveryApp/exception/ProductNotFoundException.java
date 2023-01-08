package com.fmiunibuc.FoodDeliveryApp.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(int studentId, int courseId) {
        super("No product found for restaurant with id: " + studentId );
    }
    
}